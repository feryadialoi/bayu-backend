package dev.feryadi.backend.bayu.command.user;

import dev.feryadi.backend.bayu.command.Command;
import dev.feryadi.backend.bayu.model.request.command.CreateUserCommandRequest;
import dev.feryadi.backend.bayu.model.response.UserResponse;

public interface CreateUserCommand extends Command<UserResponse, CreateUserCommandRequest> {
}
