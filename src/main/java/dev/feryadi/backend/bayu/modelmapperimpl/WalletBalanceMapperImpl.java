package dev.feryadi.backend.bayu.modelmapperimpl;

import dev.feryadi.backend.bayu.entity.Wallet;
import dev.feryadi.backend.bayu.model.response.WalletBalanceResponse;
import dev.feryadi.backend.bayu.modelmapper.BalanceMapper;
import dev.feryadi.backend.bayu.modelmapper.WalletBalanceMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class WalletBalanceMapperImpl implements WalletBalanceMapper {

    private final BalanceMapper balanceMapper;


    @Override
    public WalletBalanceResponse mapWalletToWalletBalanceResponse(Wallet wallet) {
        WalletBalanceResponse walletBalanceResponse = new WalletBalanceResponse();
        walletBalanceResponse.setId(wallet.getId());
        walletBalanceResponse.setAddress(wallet.getAddress());
        walletBalanceResponse.setBalance(balanceMapper.mapBalanceToBalanceResponse(wallet.getBalance()));

        return walletBalanceResponse;
    }
}
