package dev.feryadi.backend.bayu.service;

import dev.feryadi.backend.bayu.model.request.CreateWalletRequest;
import dev.feryadi.backend.bayu.model.request.ListWalletRequest;
import dev.feryadi.backend.bayu.model.response.WalletBalanceResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WalletService {
    List<WalletBalanceResponse> getWallets(ListWalletRequest listWalletRequest);
    WalletBalanceResponse getWallet(Long walletId);
    WalletBalanceResponse createWallet(CreateWalletRequest createWalletRequest);
}
