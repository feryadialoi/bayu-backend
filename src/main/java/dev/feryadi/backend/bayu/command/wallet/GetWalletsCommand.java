package dev.feryadi.backend.bayu.command.wallet;

import dev.feryadi.backend.bayu.command.FunctionCommand;
import dev.feryadi.backend.bayu.model.request.command.GetWalletsCommandRequest;
import dev.feryadi.backend.bayu.model.response.WalletBalanceResponse;

import java.util.List;

public interface GetWalletsCommand extends FunctionCommand<List<WalletBalanceResponse>, GetWalletsCommandRequest> {
}
