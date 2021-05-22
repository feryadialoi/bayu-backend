package dev.feryadi.backend.bayu.modelmapperimpl;

import dev.feryadi.backend.bayu.entity.Mutation;
import dev.feryadi.backend.bayu.model.response.TransferResponse;
import dev.feryadi.backend.bayu.model.response.WalletResponse;
import dev.feryadi.backend.bayu.modelmapper.TransferMapper;
import dev.feryadi.backend.bayu.modelmapper.WalletMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TransferMapperImpl implements TransferMapper {

    private final WalletMapper walletMapper;

    @Override
    public TransferResponse mapTransferToTransferResponse(Mutation mutation) {
        WalletResponse originWalletResponse = walletMapper.mapWalletToWalletResponse(mutation.getOriginWallet());
        WalletResponse destinationWalletResponse = walletMapper.mapWalletToWalletResponse(mutation.getDestinationWallet());

        TransferResponse transferResponse = new TransferResponse();
        transferResponse.setOriginWallet(originWalletResponse);
        transferResponse.setDestinationWallet(destinationWalletResponse);
        transferResponse.setAmount(mutation.getAmount());
        transferResponse.setDescription(mutation.getDescription());
        transferResponse.setCreatedDate(mutation.getCreatedDate());

        return transferResponse;
    }
}
