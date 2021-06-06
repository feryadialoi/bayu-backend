package dev.feryadi.backend.bayu.commandimpl.transfer;

import dev.feryadi.backend.bayu.auth.ApplicationUserDetails;
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

        if (transferRequest.getOriginWalletAddress().equals(transferRequest.getDestinationWalletAddress())) {
            TransferToOwnWalletException transferToOwnWalletException = new TransferToOwnWalletException("cannot transfer to its own wallet");
            transactionLogFail(transferRequest, transferToOwnWalletException);
            throw transferToOwnWalletException;
        }

        if (transferRequest.getAmount().compareTo(new BigDecimal(0)) <= 0) {
            ZeroAmountTransferException zeroAmountTransferException = new ZeroAmountTransferException("0 amount transfer is not allowed");
            transactionLogFail(transferRequest, zeroAmountTransferException);
            throw zeroAmountTransferException;
        }

        Wallet originWallet = getOriginWallet(transferRequest);
        Wallet destinationWallet = getDestinationWallet(transferRequest);

        ApplicationUserDetails userDetails = (ApplicationUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!userDetails.getId().equals(originWallet.getUser().getId())) {
            UserAndWalletNotMatchException userAndWalletNotMatchException = new UserAndWalletNotMatchException("transfer not allowed, user and wallet not match");
            transactionLogFail(transferRequest, userAndWalletNotMatchException);
            throw userAndWalletNotMatchException;
        }

        if (originWallet.getBalance().getBalance().compareTo(transferRequest.getAmount()) < 0) {
            InsufficientBalanceException insufficientBalanceException = new InsufficientBalanceException("Insufficient balance");
            transactionLogFail(transferRequest, insufficientBalanceException);
            throw insufficientBalanceException;
        }

        // subtract origin wallet balance
        BigDecimal originWalletBalance = originWallet.getBalance().getBalance();
        BigDecimal updatedOriginWalletBalance = originWallet.getBalance()
                .getBalance()
                .subtract(transferRequest.getAmount());
        originWallet.getBalance()
                .setBalance(updatedOriginWalletBalance);

        // add destination wallet balance
        BigDecimal destinationWalletBalance = destinationWallet.getBalance().getBalance();
        BigDecimal updatedDestinationWalletBalance = destinationWallet.getBalance()
                .getBalance()
                .add(transferRequest.getAmount());
        destinationWallet.getBalance()
                .setBalance(updatedDestinationWalletBalance);

        // mutation debit of destination wallet
        Mutation mutationDebit = Mutation.builder()
                .amount(transferRequest.getAmount())
                .originWallet(originWallet)
                .destinationWallet(destinationWallet)
                .description(transferRequest.getDescription())
                .initialBalance(originWalletBalance)
                .balance(updatedOriginWalletBalance)
                .type(Mutation.MutationType.DEBIT)
                .build();

        mutationDebit = mutationRepository.save(mutationDebit);

        // mutation credit of origin wallet
        Mutation mutationCredit = Mutation.builder()
                .amount(transferRequest.getAmount())
                .originWallet(destinationWallet)
                .destinationWallet(originWallet)
                .description(transferRequest.getDescription())
                .initialBalance(destinationWalletBalance)
                .balance(updatedDestinationWalletBalance)
                .type(Mutation.MutationType.CREDIT)
                .build();

        mutationCredit = mutationRepository.save(mutationCredit);




        transactionLogSuccess(transferRequest);

        return transferMapper.mapTransferToTransferResponse(mutationCredit);
    }

    private Wallet getOriginWallet(TransferRequest transferRequest) {
        return walletRepository.findByAddress(transferRequest.getOriginWalletAddress())
                .orElseThrow(() -> {
                    WalletNotFoundException walletNotFoundException = new WalletNotFoundException("origin wallet not found");
                    transactionLogFail(transferRequest, walletNotFoundException);
                    throw walletNotFoundException;
                });
    }

    private Wallet getDestinationWallet(TransferRequest transferRequest) {
        return walletRepository.findByAddress(transferRequest.getDestinationWalletAddress())
                .orElseThrow(() -> {
                    WalletNotFoundException walletNotFoundException = new WalletNotFoundException("destination wallet not found");
                    transactionLogFail(transferRequest, walletNotFoundException);
                    throw walletNotFoundException;
                });
    }

    private void transactionLogSuccess(TransferRequest transferRequest) {
        log.info("transfer transaction success");
        transactionLogService.logTransactionSuccess(
                LogTransactionRequest.builder()
                        .amount(transferRequest.getAmount())
                        .description(transferRequest.getDescription())
                        .originWalletAddress(transferRequest.getOriginWalletAddress())
                        .destinationWalletAddress(transferRequest.getDestinationWalletAddress())
                        .status(LogTransactionRequest.Status.SUCCESS)
                        .statusDetail("SUCCESS")
                        .build()
        );
    }

    private void transactionLogFail(TransferRequest transferRequest, RuntimeException exception) {
        log.error(exception.getMessage());
        transactionLogService.logTransactionFailed(
                LogTransactionRequest.builder()
                        .amount(transferRequest.getAmount())
                        .description(transferRequest.getDescription())
                        .originWalletAddress(transferRequest.getOriginWalletAddress())
                        .destinationWalletAddress(transferRequest.getDestinationWalletAddress())
                        .status(LogTransactionRequest.Status.FAIL)
                        .statusDetail(exception.getClass().getName() + " - " + exception.getMessage())
                        .build()
        );
    }
}
