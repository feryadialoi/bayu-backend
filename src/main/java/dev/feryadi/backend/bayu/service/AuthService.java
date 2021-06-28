package dev.feryadi.backend.bayu.service;

import dev.feryadi.backend.bayu.model.request.*;
import dev.feryadi.backend.bayu.model.response.LoginResponse;
import dev.feryadi.backend.bayu.model.response.RefreshTokenResponse;
import dev.feryadi.backend.bayu.model.response.RegisterResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);

    RegisterResponse register(RegisterRequest registerRequest);

    RefreshTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    void forgetPassword(ForgetPasswordRequest forgetPasswordRequest);

    void changePassword(Long userId, ChangePasswordRequest changePasswordRequest);
}
