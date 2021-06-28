package dev.feryadi.backend.bayu.command.userrole;

import dev.feryadi.backend.bayu.command.FunctionCommand;
import dev.feryadi.backend.bayu.model.request.command.GetUserRoleCommandRequest;
import dev.feryadi.backend.bayu.model.response.UserRoleResponse;

public interface GetUserRoleCommand extends FunctionCommand<UserRoleResponse, GetUserRoleCommandRequest> {
}
