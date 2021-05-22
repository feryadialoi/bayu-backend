package dev.feryadi.backend.bayu.commandimpl.transfer;

import dev.feryadi.backend.bayu.auth.ApplicationUserDetails;
import dev.feryadi.backend.bayu.command.transfer.TransferCommand;
import dev.feryadi.backend.bayu.entity.Mutation;
import dev.feryadi.backend.bayu.entity.Wallet;
import dev.feryadi.backend.bayu.model.request.TransferRequest;
import dev.feryadi.backend.bayu.model.request.command.TransferCommandRequest;
import dev.feryadi.backend.bayu.model.response.TransferResponse;
import dev.feryadi.backend.bayu.modelmapper.TransferMapper;
import dev.feryadi.backend.bayu.repository.MutationRepository;
import dev.feryadi.backend.bayu.repository.WalletRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Slf4j
@Component
@AllArgsConstructor
public class TransferCommandImpl implements TransferCommand {

    private final MutationRepository mutationRepository;
    private final WalletRepository walletRepository;
    private final TransferMapper transferMapper;

    @Transactional
    @Override
    public TransferResponse execute(TransferCommandRequest transferCommandRequest) throws Exception {
        TransferRequest transferRequest = transferCommandRequest.getTransferRequest();


        if (transferRequest.getOriginWalletAddress().equals(transferRequest.getDestinationWalletAddress())) {
            log.info("cannot transfer to its own wallet");
            throw new IllegalStateException("0 amount transfer is not allowed");
        }

        ApplicationUserDetails userDetails = (ApplicationUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Wallet originWallet = getOriginWallet(transferRequest.getOriginWalletAddress());
        Wallet destinationWallet = getDestinationWallet(transferRequest.getDestinationWalletAddress());

        if (transferRequest.getAmount().compareTo(new BigDecimal(0)) <= 0) {
            log.info("0 amount transfer is not allowed");
            throw new IllegalStateException("0 amount transfer is not allowed");
        }

        if (originWallet == null) {
            log.info("origin wallet not found");
            throw new IllegalStateException("origin wallet not found");
        }

        if (destinationWallet == null) {
            log.info("destination wallet not found");
            throw new IllegalStateException("destination wallet not found");
        }

        if (!userDetails.getId().equals(originWallet.getUser().getId())) {
            log.info("user and wallet not match");
            throw new IllegalStateException("transfer not allowed");
        }

        if (originWallet.getBalance().getBalance().compareTo(transferRequest.getAmount()) < 0) {
            log.info("Insufficient balance");
            throw new IllegalStateException("Insufficient balance");
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

        return transferMapper.mapTransferToTransferResponse(mutation);
    }

    private Wallet getOriginWallet(String originWalletAddress) {
        return walletRepository.findByAddress(originWalletAddress).orElse(null);
    }

    private Wallet getDestinationWallet(String destinationWalletAddress) {
        return walletRepository.findByAddress(destinationWalletAddress).orElse(null);
    }
}
