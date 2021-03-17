package dev.feryadi.backend.bayu.domain.v1.wallet.impl;

import dev.feryadi.backend.bayu.domain.v1.wallet.*;
import dev.feryadi.backend.bayu.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository, WalletMapper walletMapper) {
        this.walletRepository = walletRepository;
        this.walletMapper = walletMapper;
    }

    @Override
    public List<WalletResponse> getListOfWallet() {
        List<Wallet> listOfWallet = walletRepository.findAll();

        return listOfWallet.stream().map(walletMapper::walletToWalletResponse).collect(Collectors.toList());
    }

    @Override
    public WalletResponse getWallet(Long id) throws NotFoundException {
        Wallet wallet = findWalletOrThrowNotFoundException(id);

        return walletMapper.walletToWalletResponse(wallet);
    }

    @Override
    public WalletResponse storeWallet(CreateWalletRequest createWalletRequest) {
        Wallet wallet = new Wallet();

        wallet.address = createWalletRequest.address;

        walletRepository.save(wallet);

        return walletMapper.walletToWalletResponse(wallet);
    }

    @Override
    public WalletResponse updateWallet(UpdateWalletRequest updateWalletRequest, Long id) throws NotFoundException {
        Wallet wallet = findWalletOrThrowNotFoundException(id);

        wallet.address = updateWalletRequest.address != null ? updateWalletRequest.address : wallet.address;

        walletRepository.save(wallet);

        return walletMapper.walletToWalletResponse(wallet);
    }

    @Override
    public WalletResponse deleteWallet(Long id) throws NotFoundException {
        Wallet wallet = findWalletOrThrowNotFoundException(id);

        walletRepository.deleteById(id);

        return walletMapper.walletToWalletResponse(wallet);
    }

    private Wallet findWalletOrThrowNotFoundException(Long id) throws NotFoundException {
        Optional<Wallet> optionalWallet = walletRepository.findById(id);

        if (optionalWallet.isPresent()) {
            return optionalWallet.get();
        } else {
            throw new NotFoundException("wallet with id = " + id.toString() + " is not found");
        }
    }

}
