package dev.feryadi.backend.bayu.modelmapper;

import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.model.response.UserResponse;

public interface UserMapper {
    UserResponse mapUserToUserResponse(User user);
}
