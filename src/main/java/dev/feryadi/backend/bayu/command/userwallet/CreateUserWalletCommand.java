package dev.feryadi.backend.bayu.command.userwallet;

import dev.feryadi.backend.bayu.command.Command;
import dev.feryadi.backend.bayu.model.request.command.CreateUserWalletCommandRequest;
import dev.feryadi.backend.bayu.model.response.WalletBalanceResponse;

public interface CreateUserWalletCommand extends Command<WalletBalanceResponse, CreateUserWalletCommandRequest> {
}
