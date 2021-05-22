package dev.feryadi.backend.bayu.command.transaction;

import dev.feryadi.backend.bayu.command.Command;
import dev.feryadi.backend.bayu.model.request.command.GetTransactionsCommandRequest;
import dev.feryadi.backend.bayu.model.response.TransactionResponse;

import java.util.List;

public interface GetTransactionsCommand extends Command<List<TransactionResponse>, GetTransactionsCommandRequest> {
}
