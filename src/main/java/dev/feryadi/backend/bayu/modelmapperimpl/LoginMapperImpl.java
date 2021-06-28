package dev.feryadi.backend.bayu.modelmapperimpl;

import dev.feryadi.backend.bayu.model.response.LoginResponse;
import dev.feryadi.backend.bayu.modelmapper.LoginMapper;
import dev.feryadi.backend.bayu.security.ApplicationUserDetails;
import dev.feryadi.backend.bayu.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class LoginMapperImpl implements LoginMapper {

    private final JwtUtil jwtUtil;

    @Override
    public LoginResponse mapAuthenticationToLoginResponse(Authentication authentication) {
        String subject = authentication.getName();
        Long userId = ((ApplicationUserDetails) authentication.getPrincipal()).getId();
        String tokenId = UUID.randomUUID().toString();
        String accessToken = jwtUtil.generateAccessToken(userId, subject, tokenId);
        String refreshToken = jwtUtil.generateRefreshToken(tokenId);

        return LoginResponse.builder()
                .refreshToken(refreshToken)
                .accessToken(accessToken)
                .userId(userId)
                .build();
    }
}
