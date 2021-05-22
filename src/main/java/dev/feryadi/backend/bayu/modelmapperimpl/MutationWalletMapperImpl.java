package dev.feryadi.backend.bayu.modelmapperimpl;

import dev.feryadi.backend.bayu.entity.Wallet;
import dev.feryadi.backend.bayu.model.response.MutationWalletResponse;
import dev.feryadi.backend.bayu.modelmapper.MutationWalletMapper;
import org.springframework.stereotype.Component;

@Component
public class MutationWalletMapperImpl implements MutationWalletMapper {
    @Override
    public MutationWalletResponse mapWalletToMutationWallet(Wallet wallet) {

        MutationWalletResponse mutationWalletResponse = new MutationWalletResponse();
        mutationWalletResponse.setId(wallet.getId());
        mutationWalletResponse.setAddress(wallet.getAddress());

        return mutationWalletResponse;
    }
}
