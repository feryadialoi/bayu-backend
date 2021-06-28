package dev.feryadi.backend.bayu.commandimpl.auth;

import com.auth0.jwt.exceptions.TokenExpiredException;
import dev.feryadi.backend.bayu.command.auth.RefreshTokenCommand;
import dev.feryadi.backend.bayu.exception.RefreshTokenExpiredException;
import dev.feryadi.backend.bayu.exception.TokenIdNotMatchException;
import dev.feryadi.backend.bayu.model.request.command.RefreshTokenCommandRequest;
import dev.feryadi.backend.bayu.model.response.RefreshTokenResponse;
import dev.feryadi.backend.bayu.modelmapper.RefreshTokenMapper;
import dev.feryadi.backend.bayu.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RefreshTokenCommandImpl implements RefreshTokenCommand {

    private final JwtUtil jwtUtil;
    private final RefreshTokenMapper refreshTokenMapper;

    @Override
    public RefreshTokenResponse execute(RefreshTokenCommandRequest request) {

        String accessToken = request.getRefreshTokenRequest().getAccessToken();
        String refreshToken = request.getRefreshTokenRequest().getRefreshToken();

        try {
            jwtUtil.verifyToken(refreshToken);
        } catch (TokenExpiredException exception) {
            throw new RefreshTokenExpiredException("refresh token expired, please re-login");
        }

        if (!isTokenIdMatch(accessToken, refreshToken)) {
            throw new TokenIdNotMatchException("token id not match");
        }

        String generateAccessToken = jwtUtil.generateAccessToken(accessToken);

        return refreshTokenMapper.mapTokenToRefreshTokenResponse(generateAccessToken);
    }

    private Boolean isTokenIdMatch(String accessToken, String refreshToken) {
        return jwtUtil.getTokenId(accessToken).equals(jwtUtil.getTokenId(refreshToken));
    }
}
