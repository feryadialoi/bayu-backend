package dev.feryadi.backend.bayu.serviceimpl;

import dev.feryadi.backend.bayu.command.auth.*;
import dev.feryadi.backend.bayu.model.request.*;
import dev.feryadi.backend.bayu.model.request.command.*;
import dev.feryadi.backend.bayu.model.response.LoginResponse;
import dev.feryadi.backend.bayu.model.response.RefreshTokenResponse;
import dev.feryadi.backend.bayu.model.response.RegisterResponse;
import dev.feryadi.backend.bayu.service.AuthService;
import dev.feryadi.backend.bayu.service.ServiceExecutor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ServiceExecutor serviceExecutor;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        return serviceExecutor.execute(LoginCommand.class, new LoginCommandRequest(loginRequest));
    }

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        return serviceExecutor.execute(RegisterCommand.class, new RegisterCommandRequest(registerRequest));
    }

    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        return serviceExecutor.execute(RefreshTokenCommand.class, new RefreshTokenCommandRequest(refreshTokenRequest));
    }

    @Override
    public void forgetPassword(ForgetPasswordRequest forgetPasswordRequest) {
        serviceExecutor.execute(ForgetPasswordCommand.class, new ForgetPasswordCommandRequest(forgetPasswordRequest));
    }

    @Override
    public void changePassword(Long userId, ChangePasswordRequest changePasswordRequest) {
        serviceExecutor.execute(ChangePasswordCommand.class, new ChangePasswordCommandRequest(userId, changePasswordRequest));
    }
}
