package dev.feryadi.backend.bayu.service;

import dev.feryadi.backend.bayu.model.request.CreateWalletRequest;
import dev.feryadi.backend.bayu.model.response.WalletBalanceResponse;

public interface UserWalletService {
    WalletBalanceResponse getUserWallet(Long userId);

    WalletBalanceResponse createUserWallet(Long userId, CreateWalletRequest createWalletRequest);
}
