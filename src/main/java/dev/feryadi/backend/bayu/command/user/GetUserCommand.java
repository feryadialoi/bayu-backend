package dev.feryadi.backend.bayu.command.user;

import dev.feryadi.backend.bayu.command.FunctionCommand;
import dev.feryadi.backend.bayu.model.request.command.GetUserCommandRequest;
import dev.feryadi.backend.bayu.model.response.UserResponse;

public interface GetUserCommand extends FunctionCommand<UserResponse, GetUserCommandRequest> {
}
