package dev.feryadi.backend.bayu.service;

import dev.feryadi.backend.bayu.exception.AlreadyExistException;
import dev.feryadi.backend.bayu.exception.ForbiddenAccessException;
import dev.feryadi.backend.bayu.exception.NotFoundException;
import dev.feryadi.backend.bayu.model.request.CreateWalletRequest;
import dev.feryadi.backend.bayu.model.response.WalletBalanceResponse;

public interface UserWalletService {
    WalletBalanceResponse getUserWallet(Long userId) throws Exception;
    WalletBalanceResponse createUserWallet(Long userId, CreateWalletRequest createWalletRequest) throws Exception;
}
