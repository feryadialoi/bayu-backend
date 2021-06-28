package dev.feryadi.backend.bayu.command.userbalance;

import dev.feryadi.backend.bayu.command.FunctionCommand;
import dev.feryadi.backend.bayu.model.request.command.GetUserBalanceCommandRequest;
import dev.feryadi.backend.bayu.model.response.BalanceResponse;

public interface GetUserBalanceCommand extends FunctionCommand<BalanceResponse, GetUserBalanceCommandRequest> {
}
