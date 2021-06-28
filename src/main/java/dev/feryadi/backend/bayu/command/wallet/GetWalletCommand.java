package dev.feryadi.backend.bayu.command.wallet;

import dev.feryadi.backend.bayu.command.FunctionCommand;
import dev.feryadi.backend.bayu.model.request.command.GetWalletCommandRequest;
import dev.feryadi.backend.bayu.model.response.WalletBalanceResponse;

public interface GetWalletCommand extends FunctionCommand<WalletBalanceResponse, GetWalletCommandRequest> {
}
