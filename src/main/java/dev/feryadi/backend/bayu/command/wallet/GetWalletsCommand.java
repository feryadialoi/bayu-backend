package dev.feryadi.backend.bayu.command.wallet;

import dev.feryadi.backend.bayu.command.Command;
import dev.feryadi.backend.bayu.model.request.command.GetWalletsCommandRequest;
import dev.feryadi.backend.bayu.model.response.WalletBalanceResponse;

import java.util.List;

public interface GetWalletsCommand extends Command<List<WalletBalanceResponse>, GetWalletsCommandRequest> {
}
