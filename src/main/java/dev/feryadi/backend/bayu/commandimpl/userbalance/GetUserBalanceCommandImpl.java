package dev.feryadi.backend.bayu.commandimpl.userbalance;

import dev.feryadi.backend.bayu.command.userbalance.GetUserBalanceCommand;
import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.exception.UserNotFoundException;
import dev.feryadi.backend.bayu.exception.WalletNotFoundException;
import dev.feryadi.backend.bayu.model.request.command.GetUserBalanceCommandRequest;
import dev.feryadi.backend.bayu.model.response.BalanceResponse;
import dev.feryadi.backend.bayu.modelmapper.BalanceMapper;
import dev.feryadi.backend.bayu.repository.BalanceRepository;
import dev.feryadi.backend.bayu.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@AllArgsConstructor
public class GetUserBalanceCommandImpl implements GetUserBalanceCommand {

    private final UserRepository userRepository;
    private final BalanceRepository balanceRepository;
    private final BalanceMapper balanceMapper;


    @Override
    public BalanceResponse execute(GetUserBalanceCommandRequest request) {
        Long userId = request.getUserId();

        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getWallet() != null) {
                return balanceMapper.mapBalanceToBalanceResponse(user.getWallet().getBalance());
            }

            throw new WalletNotFoundException("User with id " + userId + " doesn't have wallet");
        }

        throw new UserNotFoundException("User with id " + userId + " not found");

    }
}
