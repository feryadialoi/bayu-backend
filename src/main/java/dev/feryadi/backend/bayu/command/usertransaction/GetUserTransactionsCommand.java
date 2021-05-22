package dev.feryadi.backend.bayu.command.usertransaction;

import dev.feryadi.backend.bayu.command.Command;
import dev.feryadi.backend.bayu.model.request.command.GetUserTransactionsCommandRequest;
import dev.feryadi.backend.bayu.model.response.UserTransactionResponse;

import java.util.List;

public interface GetUserTransactionsCommand extends Command<List<UserTransactionResponse>, GetUserTransactionsCommandRequest> {
}
