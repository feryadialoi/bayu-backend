package dev.feryadi.backend.bayu.command.user;

import dev.feryadi.backend.bayu.command.FunctionCommand;
import dev.feryadi.backend.bayu.model.request.command.UpdateUserCommandRequest;
import dev.feryadi.backend.bayu.model.response.UserResponse;

public interface UpdateUserCommand extends FunctionCommand<UserResponse, UpdateUserCommandRequest> {
}
