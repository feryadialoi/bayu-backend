package dev.feryadi.backend.bayu.commandimpl.transfer;

import dev.feryadi.backend.bayu.command.transfer.TransferCommand;
import dev.feryadi.backend.bayu.entity.Mutation;
import dev.feryadi.backend.bayu.entity.Wallet;
import dev.feryadi.backend.bayu.exception.*;
import dev.feryadi.backend.bayu.model.request.LogTransactionRequest;
import dev.feryadi.backend.bayu.model.request.TransferRequest;
import dev.feryadi.backend.bayu.model.request.command.TransferCommandRequest;
import dev.feryadi.backend.bayu.model.response.TransferResponse;
import dev.feryadi.backend.bayu.modelmapper.TransferMapper;
import dev.feryadi.backend.bayu.repository.MutationRepository;
import dev.feryadi.backend.bayu.repository.WalletRepository;
import dev.feryadi.backend.bayu.security.ApplicationUserDetails;
import dev.feryadi.backend.bayu.service.TransactionLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Component
@AllArgsConstructor
public class TransferCommandImpl implements TransferCommand {

    private final MutationRepository mutationRepository;
    private final WalletRepository walletRepository;
    private final TransferMapper transferMapper;
    private final TransactionLogService transactionLogService;

    @Transactional()
    @Override
    public TransferResponse execute(TransferCommandRequest transferCommandRequest) {

        TransferRequest transferRequest = transferCommandRequest.getTransferRequest();

        checkTransferReceiver(transferRequest);
        checkTransferAmount(transferRequest);

        Wallet senderWallet = getSenderWallet(transferRequest);
        Wallet receiverWallet = getReceiverWallet(transferRequest);

        checkSenderWalletIsUserWallet(transferRequest, senderWallet);
        checkSenderWalletBalance(transferRequest, senderWallet);

        BigDecimal senderWalletBalance = senderWallet.getBalance().getBalance();
        BigDecimal updatedSenderWalletBalance = subtractSenderWalletBalance(senderWallet, transferRequest);
        BigDecimal receiverWalletBalance = receiverWallet.getBalance().getBalance();
        BigDecimal updatedReceiverWalletBalance = addReceiverWalletBalance(receiverWallet, transferRequest);

        Mutation mutationDebit = saveSenderWalletMutationDebit(senderWallet, receiverWallet, transferRequest, senderWalletBalance, updatedSenderWalletBalance);
        Mutation mutationCredit = saveReceiverWalletMutationCredit(receiverWallet, senderWallet, transferRequest, receiverWalletBalance, updatedReceiverWalletBalance);

        transactionLogSuccess(transferRequest);

        return transferMapper.mapTransferToTransferResponse(mutationDebit);
    }

    private void checkSenderWalletBalance(TransferRequest transferRequest, Wallet senderWallet) {
        if (senderWallet.getBalance().getBalance().compareTo(transferRequest.getAmount()) < 0) {
            InsufficientBalanceException insufficientBalanceException = new InsufficientBalanceException("Insufficient balance");
            transactionLogFail(transferRequest, insufficientBalanceException);
            throw insufficientBalanceException;
        }
    }

    private void checkSenderWalletIsUserWallet(TransferRequest transferRequest, Wallet senderWallet) {
        ApplicationUserDetails userDetails = (ApplicationUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!userDetails.getId().equals(senderWallet.getUser().getId())) {
            UserAndWalletNotMatchException userAndWalletNotMatchException = new UserAndWalletNotMatchException("transfer not allowed, user and wallet not match");
            transactionLogFail(transferRequest, userAndWalletNotMatchException);
            throw userAndWalletNotMatchException;
        }
    }

    private void checkTransferAmount(TransferRequest transferRequest) {
        if (transferRequest.getAmount().compareTo(new BigDecimal(0)) <= 0) {
            ZeroAmountTransferException zeroAmountTransferException = new ZeroAmountTransferException("0 amount transfer is not allowed");
            transactionLogFail(transferRequest, zeroAmountTransferException);
            throw zeroAmountTransferException;
        }
    }

    private void checkTransferReceiver(TransferRequest transferRequest) {
        if (transferRequest.getSenderWalletAddress().equals(transferRequest.getReceiverWalletAddress())) {
            TransferToOwnWalletException transferToOwnWalletException = new TransferToOwnWalletException("cannot transfer to its own wallet");
            transactionLogFail(transferRequest, transferToOwnWalletException);
            throw transferToOwnWalletException;
        }
    }

    private Mutation saveSenderWalletMutationDebit(
            Wallet senderWallet,
            Wallet receiverWallet,
            TransferRequest transferRequest,
            BigDecimal senderWalletBalance,
            BigDecimal updatedSenderWalletBalance
    ) {
        Mutation mutationDebit = Mutation.builder()
                .amount(transferRequest.getAmount().negate())
                .senderWallet(senderWallet)
                .receiverWallet(receiverWallet)
                .description(transferRequest.getDescription())
                .initialBalance(senderWalletBalance)
                .balance(updatedSenderWalletBalance)
                .type(Mutation.MutationType.DEBIT)
                .build();

        return mutationRepository.save(mutationDebit);
    }

    private Mutation saveReceiverWalletMutationCredit(
            Wallet receiverWallet,
            Wallet senderWallet,
            TransferRequest transferRequest,
            BigDecimal receiverWalletBalance,
            BigDecimal updatedReceiverWalletBalance
    ) {
        Mutation mutationCredit = Mutation.builder()
                .amount(transferRequest.getAmount())
                .senderWallet(receiverWallet)
                .receiverWallet(senderWallet)
                .description(transferRequest.getDescription())
                .initialBalance(receiverWalletBalance)
                .balance(updatedReceiverWalletBalance)
                .type(Mutation.MutationType.CREDIT)
                .build();

        return mutationRepository.save(mutationCredit);
    }

    private BigDecimal subtractSenderWalletBalance(Wallet senderWallet, TransferRequest transferRequest) {

        BigDecimal updatedSenderWalletBalance = senderWallet.getBalance()
                .getBalance()
                .subtract(transferRequest.getAmount());

        senderWallet.getBalance().setBalance(updatedSenderWalletBalance);

        return updatedSenderWalletBalance;
    }

    private BigDecimal addReceiverWalletBalance(Wallet receiverWallet, TransferRequest transferRequest) {

        BigDecimal updatedReceiverWalletBalance = receiverWallet.getBalance()
                .getBalance()
                .add(transferRequest.getAmount());

        receiverWallet.getBalance().setBalance(updatedReceiverWalletBalance);

        return updatedReceiverWalletBalance;
    }

    private Wallet getSenderWallet(TransferRequest transferRequest) {
        return walletRepository.findByAddress(transferRequest.getSenderWalletAddress())
                .orElseThrow(() -> {
                    WalletNotFoundException walletNotFoundException = new WalletNotFoundException("sender wallet not found");
                    transactionLogFail(transferRequest, walletNotFoundException);
                    throw walletNotFoundException;
                });
    }

    private Wallet getReceiverWallet(TransferRequest transferRequest) {
        return walletRepository.findByAddress(transferRequest.getReceiverWalletAddress())
                .orElseThrow(() -> {
                    WalletNotFoundException walletNotFoundException = new WalletNotFoundException("receiver wallet not found");
                    transactionLogFail(transferRequest, walletNotFoundException);
                    throw walletNotFoundException;
                });
    }

    private void transactionLogSuccess(TransferRequest transferRequest) {
        log.info("transfer transaction success");

        LogTransactionRequest logTransactionRequest = LogTransactionRequest.builder()
                .amount(transferRequest.getAmount())
                .description(transferRequest.getDescription())
                .senderWalletAddress(transferRequest.getSenderWalletAddress())
                .receiverWalletAddress(transferRequest.getReceiverWalletAddress())
                .status(LogTransactionRequest.Status.SUCCESS)
                .statusDetail("SUCCESS")
                .build();

        transactionLogService.logTransactionSuccess(logTransactionRequest);
    }

    private void transactionLogFail(TransferRequest transferRequest, RuntimeException exception) {
        log.error(exception.getMessage());

        LogTransactionRequest logTransactionRequest = LogTransactionRequest.builder()
                .amount(transferRequest.getAmount())
                .description(transferRequest.getDescription())
                .senderWalletAddress(transferRequest.getSenderWalletAddress())
                .receiverWalletAddress(transferRequest.getReceiverWalletAddress())
                .status(LogTransactionRequest.Status.FAIL)
                .statusDetail(exception.getClass().getName() + " - " + exception.getMessage())
                .build();

        transactionLogService.logTransactionFailed(logTransactionRequest);
    }
}
