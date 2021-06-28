package dev.feryadi.backend.bayu.service;

import dev.feryadi.backend.bayu.model.request.CreateUserRequest;
import dev.feryadi.backend.bayu.model.request.ListUserRequest;
import dev.feryadi.backend.bayu.model.request.UpdateUserRequest;
import dev.feryadi.backend.bayu.model.response.UserResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    UserResponse createUser(CreateUserRequest createUserRequest);

    UserResponse updateUser(Long userId, UpdateUserRequest updateUserRequest);

    UserResponse getUser(Long userId);

    List<UserResponse> getUsers(ListUserRequest listUserRequest);

    UserResponse getUserByEmail(String email);

    String updateUserPhoto(Long userId, MultipartFile photo);

    Resource getUserPhoto(Long useId);
}
