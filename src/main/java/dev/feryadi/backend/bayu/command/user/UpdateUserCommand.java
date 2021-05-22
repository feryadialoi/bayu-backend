package dev.feryadi.backend.bayu.command.user;

import dev.feryadi.backend.bayu.command.Command;
import dev.feryadi.backend.bayu.model.request.command.UpdateUserCommandRequest;
import dev.feryadi.backend.bayu.model.response.UserResponse;

public interface UpdateUserCommand extends Command<UserResponse, UpdateUserCommandRequest> {
}
