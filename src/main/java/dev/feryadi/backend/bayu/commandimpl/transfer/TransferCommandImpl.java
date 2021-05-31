package dev.feryadi.backend.bayu.commandimpl.transfer;

import dev.feryadi.backend.bayu.auth.ApplicationUserDetails;
import dev.feryadi.backend.bayu.command.transfer.TransferCommand;
import dev.feryadi.backend.bayu.entity.Mutation;
import dev.feryadi.backend.bayu.entity.Wallet;
import dev.feryadi.backend.bayu.exception.InsufficientBalanceException;
import dev.feryadi.backend.bayu.exception.TransferToOwnWalletException;
import dev.feryadi.backend.bayu.exception.WalletNotFoundException;
import dev.feryadi.backend.bayu.exception.ZeroAmountTransferException;
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

        transactionLogService.logTransactionStart(LogTransactionRequest.builder()
                .originWalletAddress(transferCommandRequest.getTransferRequest().getOriginWalletAddress())
                .destinationWalletAddress(transferCommandRequest.getTransferRequest().getDestinationWalletAddress())
                .amount(transferCommandRequest.getTransferRequest().getAmount())
                .description("START - " + transferCommandRequest.getTransferRequest().getDescription())
                .build());

        TransferRequest transferRequest = transferCommandRequest.getTransferRequest();


        if (transferRequest.getOriginWalletAddress().equals(transferRequest.getDestinationWalletAddress())) {
            log.info("cannot transfer to its own wallet");
            throw new TransferToOwnWalletException("cannot transfer to its own wallet");
        }

        ApplicationUserDetails userDetails = (ApplicationUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Wallet originWallet = getOriginWallet(transferRequest.getOriginWalletAddress());
        Wallet destinationWallet = getDestinationWallet(transferRequest.getDestinationWalletAddress());

        if (transferRequest.getAmount().compareTo(new BigDecimal(0)) <= 0) {
            log.info("0 amount transfer is not allowed");
            throw new ZeroAmountTransferException("0 amount transfer is not allowed");
        }

        if (originWallet == null) {
            log.info("origin wallet not found");
            throw new WalletNotFoundException("origin wallet not found");
        }

        if (destinationWallet == null) {
            log.info("destination wallet not found");
            throw new WalletNotFoundException("destination wallet not found");
        }

        if (!userDetails.getId().equals(originWallet.getUser().getId())) {
            log.info("user and wallet not match");
            throw new IllegalStateException("transfer not allowed");
        }

        if (originWallet.getBalance().getBalance().compareTo(transferRequest.getAmount()) < 0) {
            log.info("Insufficient balance");
            throw new InsufficientBalanceException("Insufficient balance");
        }

        // subtract origin wallet balance
        originWallet.getBalance().setBalance(
                originWallet.getBalance().getBalance().subtract(transferRequest.getAmount())
        );
        walletRepository.save(originWallet);

        // add destination wallet balance
        destinationWallet.getBalance().setBalance(
                destinationWallet.getBalance().getBalance().add(transferRequest.getAmount())
        );
        walletRepository.save(destinationWallet);

        // save mutation
        Mutation mutation = new Mutation();
        mutation.setAmount(transferRequest.getAmount());
        mutation.setOriginWallet(originWallet);
        mutation.setDestinationWallet(destinationWallet);
        mutation.setDescription(transferRequest.getDescription());
        mutationRepository.save(mutation);


        transactionLogService.logTransactionSuccess(LogTransactionRequest.builder()
                .originWalletAddress(transferCommandRequest.getTransferRequest().getOriginWalletAddress())
                .destinationWalletAddress(transferCommandRequest.getTransferRequest().getDestinationWalletAddress())
                .amount(transferCommandRequest.getTransferRequest().getAmount())
                .description("SUCCESS - " + transferCommandRequest.getTransferRequest().getDescription())
                .build());

        return transferMapper.mapTransferToTransferResponse(mutation);
    }

    private Wallet getOriginWallet(String originWalletAddress) {
        return walletRepository.findByAddress(originWalletAddress).orElse(null);
    }

    private Wallet getDestinationWallet(String destinationWalletAddress) {
        return walletRepository.findByAddress(destinationWalletAddress).orElse(null);
    }
}
