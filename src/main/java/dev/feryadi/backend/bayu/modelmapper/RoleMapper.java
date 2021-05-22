package dev.feryadi.backend.bayu.modelmapper;

import dev.feryadi.backend.bayu.entity.roleandpermission.Role;
import dev.feryadi.backend.bayu.model.response.RoleResponse;

public interface RoleMapper {
    RoleResponse mapRoleToRoleResponse(Role role);
}
