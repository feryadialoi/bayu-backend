package dev.feryadi.backend.bayu.command.pin;

import dev.feryadi.backend.bayu.command.Command;
import dev.feryadi.backend.bayu.model.request.command.CreateUserPinCommandRequest;
import dev.feryadi.backend.bayu.model.response.PinResponse;

public interface CreateUserPinCommand extends Command<PinResponse, CreateUserPinCommandRequest> {
}
