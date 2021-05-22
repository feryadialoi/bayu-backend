package dev.feryadi.backend.bayu.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    public String generateToken(Long id, String subject) {
        Date expiresAt = new Date(System.currentTimeMillis() + JwtAuthenticationConfig.EXPIRATION_TIME);

        Algorithm sign = Algorithm.HMAC512(JwtAuthenticationConfig.KEY.getBytes());

        return JWT.create()
                .withSubject(subject)
                .withIssuedAt(new Date())
                .withClaim("tokenId", UUID.randomUUID().toString())
                .withClaim("userId", id)
                .withExpiresAt(expiresAt)
                .sign(sign);
    }

    public DecodedJWT verifyToken(String token) {
        return JWT.require(Algorithm.HMAC512(JwtAuthenticationConfig.KEY.getBytes()))
                .build()
                .verify(token);
    }

    public Long getUserId(DecodedJWT decodedJWT) {
        return decodedJWT.getClaim("userId").asLong();
    }

    public String getSubject(DecodedJWT decodedJWT) {
        return decodedJWT.getSubject();
    }
}
