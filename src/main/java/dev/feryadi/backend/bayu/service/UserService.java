package dev.feryadi.backend.bayu.service;

import dev.feryadi.backend.bayu.model.request.CreateUserRequest;
import dev.feryadi.backend.bayu.model.request.ListUserRequest;
import dev.feryadi.backend.bayu.model.request.UpdateUserRequest;
import dev.feryadi.backend.bayu.model.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(CreateUserRequest createUserRequest) throws Exception;
    UserResponse updateUser(Long userId, UpdateUserRequest updateUserRequest) throws Exception;
    UserResponse getUser(Long userId) throws Exception;
    List<UserResponse> getUsers(ListUserRequest listUserRequest) throws Exception;
    UserResponse getUserByEmail(String email) throws Exception;

}
