package dev.feryadi.backend.bayu.command.user;

import dev.feryadi.backend.bayu.command.Command;
import dev.feryadi.backend.bayu.model.request.command.GetUserByEmailCommandRequest;
import dev.feryadi.backend.bayu.model.response.UserResponse;

public interface GetUserByEmailCommand extends Command<UserResponse, GetUserByEmailCommandRequest> {
}
