package dev.feryadi.backend.bayu.commandimpl.userwallet;

import dev.feryadi.backend.bayu.command.userwallet.CreateUserWalletCommand;
import dev.feryadi.backend.bayu.entity.Balance;
import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.entity.Wallet;
import dev.feryadi.backend.bayu.exception.UserWalletAlreadyExistsException;
import dev.feryadi.backend.bayu.exception.UserNotFoundException;
import dev.feryadi.backend.bayu.model.request.command.CreateUserWalletCommandRequest;
import dev.feryadi.backend.bayu.model.response.WalletBalanceResponse;
import dev.feryadi.backend.bayu.modelmapper.WalletBalanceMapper;
import dev.feryadi.backend.bayu.repository.BalanceRepository;
import dev.feryadi.backend.bayu.repository.UserRepository;
import dev.feryadi.backend.bayu.repository.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class CreateUserWalletCommandImpl implements CreateUserWalletCommand {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final BalanceRepository balanceRepository;
    private final WalletBalanceMapper walletBalanceMapper;

    @Transactional
    @Override
    public WalletBalanceResponse execute(CreateUserWalletCommandRequest request) {


        Optional<User> optionalUser = userRepository.findById(request.getUserId());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (user.getWallet() == null) {

                Wallet wallet = new Wallet();
                wallet.setAddress(UUID.randomUUID().toString());
                wallet.setUser(user);
                wallet = walletRepository.save(wallet);


                Balance balance = new Balance();
                balance.setBalance(new BigDecimal("1000000"));
                balance.setInitialBalance(new BigDecimal("1000000"));
                balance.setWallet(wallet);
                balance = balanceRepository.save(balance);

                wallet.setBalance(balance);

                return walletBalanceMapper.mapWalletToWalletBalanceResponse(wallet);
            }

            throw new UserWalletAlreadyExistsException("User with id " + request.getUserId() + " already has a wallet");
        }

        throw new UserNotFoundException("user with id " + request.getUserId() + " not found");
    }
}
