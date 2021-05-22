package dev.feryadi.backend.bayu.modelmapperimpl;

import dev.feryadi.backend.bayu.entity.Wallet;
import dev.feryadi.backend.bayu.model.response.WalletBalanceResponse;
import dev.feryadi.backend.bayu.model.response.WalletResponse;
import dev.feryadi.backend.bayu.modelmapper.BalanceMapper;
import dev.feryadi.backend.bayu.modelmapper.WalletMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class WalletMapperImpl implements WalletMapper {

    @Override
    public WalletResponse mapWalletToWalletResponse(Wallet wallet) {
        WalletResponse walletResponse = new WalletResponse();
        walletResponse.setId(wallet.getId());
        walletResponse.setAddress(wallet.getAddress());

        return walletResponse;
    }
}
