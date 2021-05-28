package dev.feryadi.backend.bayu.serviceimpl;

import dev.feryadi.backend.bayu.command.wallet.GetWalletCommand;
import dev.feryadi.backend.bayu.command.wallet.GetWalletsCommand;
import dev.feryadi.backend.bayu.entity.Wallet;
import dev.feryadi.backend.bayu.model.request.CreateWalletRequest;
import dev.feryadi.backend.bayu.model.request.ListWalletRequest;
import dev.feryadi.backend.bayu.model.request.command.GetWalletCommandRequest;
import dev.feryadi.backend.bayu.model.request.command.GetWalletsCommandRequest;
import dev.feryadi.backend.bayu.model.response.WalletBalanceResponse;
import dev.feryadi.backend.bayu.modelmapper.WalletBalanceMapper;
import dev.feryadi.backend.bayu.modelmapper.WalletMapper;
import dev.feryadi.backend.bayu.repository.WalletRepository;
import dev.feryadi.backend.bayu.service.ServiceExecutor;
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

    private final ServiceExecutor serviceExecutor;

    @Override
    public List<WalletBalanceResponse> getWallets(ListWalletRequest listWalletRequest) {
        return serviceExecutor.execute(GetWalletsCommand.class, new GetWalletsCommandRequest(listWalletRequest));
    }

    @Override
    public WalletBalanceResponse getWallet(Long walletId) {
        return serviceExecutor.execute(GetWalletCommand.class, new GetWalletCommandRequest(walletId));
    }

    @Override
    public WalletBalanceResponse createWallet(CreateWalletRequest createWalletRequest) {
        return null;
    }
}
