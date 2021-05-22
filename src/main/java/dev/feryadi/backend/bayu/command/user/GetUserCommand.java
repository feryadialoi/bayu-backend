package dev.feryadi.backend.bayu.command.user;

import dev.feryadi.backend.bayu.command.Command;
import dev.feryadi.backend.bayu.model.request.command.GetUserCommandRequest;
import dev.feryadi.backend.bayu.model.response.UserResponse;

public interface GetUserCommand extends Command<UserResponse, GetUserCommandRequest> {
}
