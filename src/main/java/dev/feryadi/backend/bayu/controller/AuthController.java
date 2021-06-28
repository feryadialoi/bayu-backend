package dev.feryadi.backend.bayu.controller;

import dev.feryadi.backend.bayu.model.request.*;
import dev.feryadi.backend.bayu.model.response.ApiResponse;
import dev.feryadi.backend.bayu.model.response.LoginResponse;
import dev.feryadi.backend.bayu.model.response.RefreshTokenResponse;
import dev.feryadi.backend.bayu.model.response.RegisterResponse;
import dev.feryadi.backend.bayu.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.lang.model.type.NullType;
import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class AuthController extends BaseController {

    private final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest loginRequest) {
        return createResponse(
                "Login success",
                authService.login(loginRequest)
        );
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterResponse>> register(@Valid @RequestBody RegisterRequest registerRequest) {
        return createResponse(
                HttpStatus.CREATED,
                "User register success",
                authService.register(registerRequest)
        );
    }

    @PostMapping(value = "/refresh-token")
    public ResponseEntity<ApiResponse<RefreshTokenResponse>> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return createResponse(
                "Token refreshed successfully",
                authService.refreshToken(refreshTokenRequest)
        );
    }

    @PostMapping(value = "/forget-password")
    public ResponseEntity<ApiResponse<NullType>> forgetPassword(@Valid @RequestBody ForgetPasswordRequest forgetPasswordRequest) {
        authService.forgetPassword(forgetPasswordRequest);
        return createResponse("Forget Password request successfully", null);
    }

    @PreAuthorize("hasRole('ADMIN') or @userSecurity.hasUserId(authentication, #userId)")
    @PostMapping(value = "/change-password/{userId}")
    public ResponseEntity<ApiResponse<NullType>> changePassword(
            @PathVariable("userId") Long userId,
            @Valid @RequestBody ChangePasswordRequest changePasswordRequest
    ) {
        authService.changePassword(userId, changePasswordRequest);
        return createResponse("Change Password successfully", null);
    }

}
