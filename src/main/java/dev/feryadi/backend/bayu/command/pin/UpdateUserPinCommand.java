package dev.feryadi.backend.bayu.command.pin;

import dev.feryadi.backend.bayu.command.FunctionCommand;
import dev.feryadi.backend.bayu.model.request.command.UpdatePinCommandRequest;
import dev.feryadi.backend.bayu.model.response.PinResponse;

public interface UpdateUserPinCommand extends FunctionCommand<PinResponse, UpdatePinCommandRequest> {
}
