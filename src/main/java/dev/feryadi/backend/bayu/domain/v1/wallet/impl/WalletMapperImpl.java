package dev.feryadi.backend.bayu.domain.v1.wallet.impl;

import dev.feryadi.backend.bayu.domain.v1.wallet.Wallet;
import dev.feryadi.backend.bayu.domain.v1.wallet.WalletMapper;
import dev.feryadi.backend.bayu.domain.v1.wallet.WalletResponse;
import org.springframework.stereotype.Service;

@Service
public class WalletMapperImpl implements WalletMapper {

    @Override
    public WalletResponse walletToWalletResponse(Wallet wallet) {
        return new WalletResponse(
                wallet.id,
                wallet.address
        );
    }
}
