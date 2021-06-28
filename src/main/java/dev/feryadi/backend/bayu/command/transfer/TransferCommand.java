package dev.feryadi.backend.bayu.command.transfer;

import dev.feryadi.backend.bayu.command.FunctionCommand;
import dev.feryadi.backend.bayu.model.request.command.TransferCommandRequest;
import dev.feryadi.backend.bayu.model.response.TransferResponse;

public interface TransferCommand extends FunctionCommand<TransferResponse, TransferCommandRequest> {
}
