package dev.feryadi.backend.bayu.modelmapper;

import dev.feryadi.backend.bayu.entity.Wallet;
import dev.feryadi.backend.bayu.model.response.MutationWalletResponse;

public interface MutationWalletMapper {
    MutationWalletResponse mapWalletToMutationWallet(Wallet wallet);
}
