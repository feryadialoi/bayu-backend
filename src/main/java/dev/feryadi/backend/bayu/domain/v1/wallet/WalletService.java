package dev.feryadi.backend.bayu.domain.v1.wallet;

import dev.feryadi.backend.bayu.domain.v1.wallet.WalletResponse;

import java.util.List;

public interface WalletService {
    List<WalletResponse> getListOfWallet();

    WalletResponse getWallet(Long id) throws Exception;

    WalletResponse storeWallet(CreateWalletRequest createWalletRequest);

    WalletResponse updateWallet(UpdateWalletRequest updateWalletRequest, Long id) throws Exception;

    WalletResponse deleteWallet(Long id) throws Exception;
}
