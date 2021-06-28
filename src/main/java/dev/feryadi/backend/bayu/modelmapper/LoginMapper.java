package dev.feryadi.backend.bayu.modelmapper;

import dev.feryadi.backend.bayu.model.response.LoginResponse;
import org.springframework.security.core.Authentication;

public interface LoginMapper {
    LoginResponse mapAuthenticationToLoginResponse(Authentication authentication);
}
