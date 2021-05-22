package dev.feryadi.backend.bayu.modelmapper;

import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.model.response.UserRoleResponse;

public interface UserRoleMapper {
    UserRoleResponse mapUserToUserRoleResponse(User user);
}
