package dev.feryadi.backend.bayu.modelmapper;

import dev.feryadi.backend.bayu.entity.Wallet;
import dev.feryadi.backend.bayu.model.response.WalletResponse;

public interface WalletMapper {
    WalletResponse mapWalletToWalletResponse(Wallet wallet);
}
