package dev.feryadi.backend.bayu.command.userwallet;

import dev.feryadi.backend.bayu.command.Command;
import dev.feryadi.backend.bayu.model.request.command.GetUserWalletCommandRequest;
import dev.feryadi.backend.bayu.model.response.WalletBalanceResponse;

public interface GetUserWalletCommand extends Command<WalletBalanceResponse, GetUserWalletCommandRequest> {
}
