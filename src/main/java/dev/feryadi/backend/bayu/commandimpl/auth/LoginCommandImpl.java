package dev.feryadi.backend.bayu.commandimpl.auth;

import dev.feryadi.backend.bayu.command.auth.LoginCommand;
import dev.feryadi.backend.bayu.exception.LoginFailedException;
import dev.feryadi.backend.bayu.model.request.LoginRequest;
import dev.feryadi.backend.bayu.model.request.command.LoginCommandRequest;
import dev.feryadi.backend.bayu.model.response.LoginResponse;
import dev.feryadi.backend.bayu.modelmapper.LoginMapper;
import dev.feryadi.backend.bayu.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@AllArgsConstructor
public class LoginCommandImpl implements LoginCommand {

    private final AuthenticationManager authenticationManager;
    private final LoginMapper loginMapper;

    @Override
    public LoginResponse execute(LoginCommandRequest commandRequest) {

        LoginRequest loginRequest = commandRequest.getLoginRequest();

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword(),
                new ArrayList<>()
        );

        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            return loginMapper.mapAuthenticationToLoginResponse(authentication);

        } catch (RuntimeException exception) {
//            InternalAuthenticationServiceException
//            BadCredentialsException
//            exception.printStackTrace();
            throw new LoginFailedException("bad credentials");
        }
    }
}
