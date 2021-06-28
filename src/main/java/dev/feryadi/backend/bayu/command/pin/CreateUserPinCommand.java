package dev.feryadi.backend.bayu.command.pin;

import dev.feryadi.backend.bayu.command.FunctionCommand;
import dev.feryadi.backend.bayu.model.request.command.CreateUserPinCommandRequest;
import dev.feryadi.backend.bayu.model.response.PinResponse;

public interface CreateUserPinCommand extends FunctionCommand<PinResponse, CreateUserPinCommandRequest> {
}
