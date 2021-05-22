package dev.feryadi.backend.bayu.serviceimpl;

import dev.feryadi.backend.bayu.command.userwallet.CreateUserWalletCommand;
import dev.feryadi.backend.bayu.command.userwallet.GetUserWalletCommand;
import dev.feryadi.backend.bayu.entity.Balance;
import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.entity.Wallet;
import dev.feryadi.backend.bayu.exception.AlreadyExistException;
import dev.feryadi.backend.bayu.exception.ForbiddenAccessException;
import dev.feryadi.backend.bayu.exception.NotFoundException;
import dev.feryadi.backend.bayu.model.request.CreateWalletRequest;
import dev.feryadi.backend.bayu.model.request.command.CreateUserWalletCommandRequest;
import dev.feryadi.backend.bayu.model.request.command.GetUserWalletCommandRequest;
import dev.feryadi.backend.bayu.model.response.WalletBalanceResponse;
import dev.feryadi.backend.bayu.modelmapper.WalletBalanceMapper;
import dev.feryadi.backend.bayu.modelmapper.WalletMapper;
import dev.feryadi.backend.bayu.repository.BalanceRepository;
import dev.feryadi.backend.bayu.repository.UserRepository;
import dev.feryadi.backend.bayu.repository.WalletRepository;
import dev.feryadi.backend.bayu.service.ServiceExecutor;
import dev.feryadi.backend.bayu.service.UserWalletService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserWalletServiceImpl implements UserWalletService {

    private final ServiceExecutor serviceExecutor;

    @Override
    public WalletBalanceResponse getUserWallet(Long userId) throws Exception {
        return serviceExecutor.execute(GetUserWalletCommand.class, new GetUserWalletCommandRequest(userId));
    }

    @Override
    public WalletBalanceResponse createUserWallet(Long userId, CreateWalletRequest createWalletRequest) throws Exception {
        return serviceExecutor.execute(CreateUserWalletCommand.class, new CreateUserWalletCommandRequest(userId, createWalletRequest));
    }
}
