package dev.feryadi.backend.bayu.commandimpl.wallet;

import dev.feryadi.backend.bayu.command.wallet.GetWalletCommand;
import dev.feryadi.backend.bayu.entity.Wallet;
import dev.feryadi.backend.bayu.model.request.command.GetWalletCommandRequest;
import dev.feryadi.backend.bayu.model.response.WalletBalanceResponse;
import dev.feryadi.backend.bayu.modelmapper.WalletBalanceMapper;
import dev.feryadi.backend.bayu.repository.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class GetWalletCommandImpl implements GetWalletCommand {

    private final WalletRepository walletRepository;
    private final WalletBalanceMapper walletBalanceMapper;

    @Override
    public WalletBalanceResponse execute(GetWalletCommandRequest request) {
        Optional<Wallet> optionalWallet = walletRepository.findById(request.getWalletId());
        return optionalWallet.map(walletBalanceMapper::mapWalletToWalletBalanceResponse).orElse(null);
    }
}
