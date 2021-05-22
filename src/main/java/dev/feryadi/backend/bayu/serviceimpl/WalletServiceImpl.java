package dev.feryadi.backend.bayu.serviceimpl;

import dev.feryadi.backend.bayu.entity.Wallet;
import dev.feryadi.backend.bayu.model.request.CreateWalletRequest;
import dev.feryadi.backend.bayu.model.request.ListWalletRequest;
import dev.feryadi.backend.bayu.model.response.WalletBalanceResponse;
import dev.feryadi.backend.bayu.modelmapper.WalletBalanceMapper;
import dev.feryadi.backend.bayu.modelmapper.WalletMapper;
import dev.feryadi.backend.bayu.repository.WalletRepository;
import dev.feryadi.backend.bayu.service.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final WalletBalanceMapper walletBalanceMapper;



    @Override
    public List<WalletBalanceResponse> getWallets(ListWalletRequest listWalletRequest) {
        PageRequest pageRequest = PageRequest.of(listWalletRequest.getPage(), listWalletRequest.getSize());
        Page<Wallet> pageOfWallet = walletRepository.findAll(pageRequest);
        List<Wallet> wallets = pageOfWallet.get().collect(Collectors.toList());

        return wallets.stream().map(walletBalanceMapper::mapWalletToWalletBalanceResponse).collect(Collectors.toList());
    }

    @Override
    public WalletBalanceResponse getWallet(Long walletId) {
        Optional<Wallet> optionalWallet = walletRepository.findById(walletId);
        return optionalWallet.map(walletBalanceMapper::mapWalletToWalletBalanceResponse).orElse(null);
    }

    @Override
    public WalletBalanceResponse createWallet(CreateWalletRequest createWalletRequest) {

        return null;
    }
}
