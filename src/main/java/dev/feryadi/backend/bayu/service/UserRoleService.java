package dev.feryadi.backend.bayu.service;

import dev.feryadi.backend.bayu.model.response.UserRoleResponse;

public interface UserRoleService {
    UserRoleResponse getUserRole(Long userId);
}
