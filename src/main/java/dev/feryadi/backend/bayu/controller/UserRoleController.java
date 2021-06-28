package dev.feryadi.backend.bayu.controller;

import dev.feryadi.backend.bayu.model.response.ApiResponse;
import dev.feryadi.backend.bayu.model.response.UserRoleResponse;
import dev.feryadi.backend.bayu.service.UserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserRoleController extends BaseController {

    private final UserRoleService userRoleService;

    @PreAuthorize("hasRole('ADMIN') or @userSecurity.hasUserId(authentication, #userId)")
    @GetMapping(value = "/api/v1/users/{userId}/roles")
    public ResponseEntity<ApiResponse<UserRoleResponse>> getUserRole(@PathVariable(value = "userId") Long userId) {
        return createResponse(
                "Get user with role successfully",
                userRoleService.getUserRole(userId)
        );
    }
}
