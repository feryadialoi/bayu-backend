package dev.feryadi.backend.bayu.controller;

import dev.feryadi.backend.bayu.model.request.CreateUserRequest;
import dev.feryadi.backend.bayu.model.request.ListUserRequest;
import dev.feryadi.backend.bayu.model.request.UpdateUserRequest;
import dev.feryadi.backend.bayu.model.response.ApiResponse;
import dev.feryadi.backend.bayu.model.response.UserResponse;
import dev.feryadi.backend.bayu.security.annotation.IsAdmin;
import dev.feryadi.backend.bayu.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
public class UserController extends BaseController {

    private final UserService userService;

    @IsAdmin
    @GetMapping(value = {"/api/v1/users"})
    public ResponseEntity<ApiResponse<List<UserResponse>>> getUsers(
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "roleName", required = false) String roleName
    ) {
        ListUserRequest listUserRequest = ListUserRequest.builder()
                .size(size)
                .page(page)
                .sort(sort)
                .name(name)
                .username(username)
                .email(email)
                .phone(phone)
                .roleName(roleName)
                .build();

        return createResponse(
                HttpStatus.OK,
                "Get list of user successfully",
                userService.getUsers(listUserRequest)
        );
    }

    @PostMapping(value = {"/api/v1/users"})
    public ResponseEntity<ApiResponse<UserResponse>> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) throws Exception {
        return createResponse(
                HttpStatus.CREATED,
                "User created successfully",
                userService.createUser(createUserRequest)
        );
    }

    @PreAuthorize("hasRole('ADMIN') or @userSecurity.hasUserId(authentication, #userId)")
    @GetMapping(value = {"/api/v1/users/{userId}"})
    public ResponseEntity<ApiResponse<UserResponse>> getUser(@PathVariable(value = "userId") Long userId) throws Exception {
        return createResponse(
                HttpStatus.OK,
                "User updated successfully",
                userService.getUser(userId)
        );
    }

    @PreAuthorize("hasRole('ADMIN') or @userSecurity.hasUserId(authentication, #userId)")
    @PutMapping(value = {"/api/v1/users/{userId}"})
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(@PathVariable(value = "userId") Long userId, @Valid @RequestBody UpdateUserRequest updateUserRequest) throws Exception {
        return createResponse(
                HttpStatus.OK,
                "User updated successfully",
                userService.updateUser(userId, updateUserRequest)
        );
    }

}
