package dev.feryadi.backend.bayu.command.user;

import dev.feryadi.backend.bayu.command.FunctionCommand;
import dev.feryadi.backend.bayu.model.request.command.GetUsersCommandRequest;
import dev.feryadi.backend.bayu.model.response.UserResponse;

import java.util.List;

public interface GetUsersCommand extends FunctionCommand<List<UserResponse>, GetUsersCommandRequest> {
}
