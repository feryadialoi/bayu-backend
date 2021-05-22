package dev.feryadi.backend.bayu.command.usertransaction;

import dev.feryadi.backend.bayu.command.Command;
import dev.feryadi.backend.bayu.model.request.command.GetUserTransactionCommandRequest;
import dev.feryadi.backend.bayu.model.response.UserTransactionResponse;

public interface GetUserTransactionCommand extends Command<UserTransactionResponse, GetUserTransactionCommandRequest> {
}
