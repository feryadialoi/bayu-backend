package dev.feryadi.backend.bayu.command.userrole;

import dev.feryadi.backend.bayu.command.Command;
import dev.feryadi.backend.bayu.model.request.command.GetUserRoleCommandRequest;
import dev.feryadi.backend.bayu.model.response.UserRoleResponse;

public interface GetUserRoleCommand extends Command<UserRoleResponse, GetUserRoleCommandRequest> {
}
