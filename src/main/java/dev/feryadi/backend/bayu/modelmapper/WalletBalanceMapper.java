package dev.feryadi.backend.bayu.modelmapper;

import dev.feryadi.backend.bayu.entity.Wallet;
import dev.feryadi.backend.bayu.model.response.WalletBalanceResponse;

public interface WalletBalanceMapper {
    WalletBalanceResponse mapWalletToWalletBalanceResponse(Wallet wallet);
}
