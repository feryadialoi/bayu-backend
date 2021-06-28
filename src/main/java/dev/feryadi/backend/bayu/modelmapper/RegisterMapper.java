package dev.feryadi.backend.bayu.modelmapper;

import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.model.response.RegisterResponse;

public interface RegisterMapper {
    RegisterResponse mapUserToRegisterResponse(User user);
}
