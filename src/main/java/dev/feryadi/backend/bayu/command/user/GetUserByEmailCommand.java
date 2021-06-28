package dev.feryadi.backend.bayu.command.user;

import dev.feryadi.backend.bayu.command.FunctionCommand;
import dev.feryadi.backend.bayu.model.request.command.GetUserByEmailCommandRequest;
import dev.feryadi.backend.bayu.model.response.UserResponse;

public interface GetUserByEmailCommand extends FunctionCommand<UserResponse, GetUserByEmailCommandRequest> {
}
