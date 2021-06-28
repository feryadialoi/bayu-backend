package dev.feryadi.backend.bayu.command.transactionlog;

import dev.feryadi.backend.bayu.command.FunctionCommand;
import dev.feryadi.backend.bayu.model.request.command.LogTransactionCommandRequest;
import dev.feryadi.backend.bayu.model.response.LogTransactionResponse;

public interface LogTransactionCommand extends FunctionCommand<LogTransactionResponse, LogTransactionCommandRequest> {
}
