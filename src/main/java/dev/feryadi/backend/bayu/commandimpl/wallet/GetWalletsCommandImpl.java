package dev.feryadi.backend.bayu.commandimpl.wallet;

import dev.feryadi.backend.bayu.command.wallet.GetWalletsCommand;
import dev.feryadi.backend.bayu.entity.Wallet;
import dev.feryadi.backend.bayu.model.request.ListWalletRequest;
import dev.feryadi.backend.bayu.model.request.command.GetWalletsCommandRequest;
import dev.feryadi.backend.bayu.model.response.WalletBalanceResponse;
import dev.feryadi.backend.bayu.modelmapper.WalletBalanceMapper;
import dev.feryadi.backend.bayu.repository.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class GetWalletsCommandImpl implements GetWalletsCommand {

    private final WalletRepository walletRepository;
    private final WalletBalanceMapper walletBalanceMapper;


    @Override
    public List<WalletBalanceResponse> execute(GetWalletsCommandRequest request) {
        Page<Wallet> pageOfWallet = walletRepository.findAll(request.getListWalletRequest().getPageable());
        List<Wallet> wallets = pageOfWallet.get().collect(Collectors.toList());

        return wallets.stream().map(walletBalanceMapper::mapWalletToWalletBalanceResponse).collect(Collectors.toList());
    }
}
