package dev.feryadi.backend.bayu.controller;

import dev.feryadi.backend.bayu.model.request.CreateUserPinRequest;
import dev.feryadi.backend.bayu.model.request.UpdateUserPinRequest;
import dev.feryadi.backend.bayu.model.response.ApiResponse;
import dev.feryadi.backend.bayu.model.response.PinResponse;
import dev.feryadi.backend.bayu.service.UserPinService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserPinController extends BaseController {

    private final UserPinService userPinService;

    @PreAuthorize("hasRole('ADMIN') or @userSecurity.hasUserId(authentication, #userId)")
    @PostMapping(value = "/api/v1/users/{userId}/pins")
    public ResponseEntity<ApiResponse<PinResponse>> createUserPin(
            @PathVariable(value = "userId") Long userId,
            @RequestBody CreateUserPinRequest createUserPinRequest
    ) {
        return createResponse(
                "User pin created successfully",
                userPinService.createUserPin(userId, createUserPinRequest)
        );
    }

    @PreAuthorize("hasRole('ADMIN') or @userSecurity.hasUserId(authentication, #userId)")
    @PostMapping(value = "/api/v1/users/{userId}/pins/{pinId}")
    public ResponseEntity<ApiResponse<PinResponse>> updateUserPin(
            @PathVariable(value = "userId") Long userId,
            @PathVariable(value = "pinId") Long pinId,
            @RequestBody UpdateUserPinRequest updateUserPinRequest
    ) {
        return createResponse(
                "User pin created successfully",
                userPinService.updateUserPin(userId, pinId, updateUserPinRequest)
        );
    }
}
