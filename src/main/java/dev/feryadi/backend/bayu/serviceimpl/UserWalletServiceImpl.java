package dev.feryadi.backend.bayu.serviceimpl;

import dev.feryadi.backend.bayu.command.userwallet.CreateUserWalletCommand;
import dev.feryadi.backend.bayu.command.userwallet.GetUserWalletCommand;
import dev.feryadi.backend.bayu.model.request.CreateWalletRequest;
import dev.feryadi.backend.bayu.model.request.command.CreateUserWalletCommandRequest;
import dev.feryadi.backend.bayu.model.request.command.GetUserWalletCommandRequest;
import dev.feryadi.backend.bayu.model.response.WalletBalanceResponse;
import dev.feryadi.backend.bayu.service.ServiceExecutor;
import dev.feryadi.backend.bayu.service.UserWalletService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserWalletServiceImpl implements UserWalletService {

    private final ServiceExecutor serviceExecutor;

    @Override
    public WalletBalanceResponse getUserWallet(Long userId) {
        return serviceExecutor.execute(GetUserWalletCommand.class, new GetUserWalletCommandRequest(userId));
    }

    @Override
    public WalletBalanceResponse createUserWallet(Long userId, CreateWalletRequest createWalletRequest) {
        return serviceExecutor.execute(CreateUserWalletCommand.class, new CreateUserWalletCommandRequest(userId, createWalletRequest));
    }
}
