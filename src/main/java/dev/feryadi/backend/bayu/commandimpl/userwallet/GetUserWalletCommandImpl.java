package dev.feryadi.backend.bayu.commandimpl.userwallet;

import dev.feryadi.backend.bayu.command.userwallet.GetUserWalletCommand;
import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.entity.Wallet;
import dev.feryadi.backend.bayu.exception.NotFoundException;
import dev.feryadi.backend.bayu.model.request.command.GetUserWalletCommandRequest;
import dev.feryadi.backend.bayu.model.response.WalletBalanceResponse;
import dev.feryadi.backend.bayu.modelmapper.WalletBalanceMapper;
import dev.feryadi.backend.bayu.repository.BalanceRepository;
import dev.feryadi.backend.bayu.repository.UserRepository;
import dev.feryadi.backend.bayu.repository.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class GetUserWalletCommandImpl implements GetUserWalletCommand {

    private final UserRepository userRepository;
    private final WalletBalanceMapper walletBalanceMapper;

    @Override
    public WalletBalanceResponse execute(GetUserWalletCommandRequest request) throws Exception {
        Optional<User> optionalUser = userRepository.findById(request.getUserId());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Wallet wallet = user.getWallet();
            if (wallet != null) {
                return walletBalanceMapper.mapWalletToWalletBalanceResponse(wallet);
            }

            throw new NotFoundException("wallet of user with id " + request.getUserId() + " not found");
        }

        throw new NotFoundException("user with id " + request.getUserId() + " not found");

    }
}
