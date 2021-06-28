package dev.feryadi.backend.bayu.auth;

import com.auth0.jwt.interfaces.DecodedJWT;
import dev.feryadi.backend.bayu.security.JwtUtil;
import org.junit.jupiter.api.Test;

class JwtUtilTest {
    String refreshToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJ0b2tlbklkIjoiY2Y0NzljYjAtZmJkYi00ODAzLTg2MTctYmMwNmQyYzM3NmViIiwiZXhwIjoxNjI3MTEzNzc0LCJpYXQiOjE2MjQ1MjE3NzR9.yRH0HXzV0Mr1zL5L7nJVbp60wryczkCZ6zpVToDquLArFBoxJxHChh7G7s-Fy6efgN44PgC0enZMAo4GC4vJ6Q";
    String accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmZXJ5YWRpYWxvaSIsInRva2VuSWQiOiJjZjQ3OWNiMC1mYmRiLTQ4MDMtODYxNy1iYzA2ZDJjMzc2ZWIiLCJleHAiOjE2MjQ1MjUzNzQsImlhdCI6MTYyNDUyMTc3NCwidXNlcklkIjoxfQ.ajzuA5WGcjaDRL6q7PkQ2socX93s0JI5hSzYX2fSa8tQn7VkO1cf-QOYyZKM6II0ZhmaDpHLz2RtfLHqsI0ZZw";

    JwtUtil jwtUtil = new JwtUtil();

    @Test
    void test() {
        DecodedJWT decodedJWT = jwtUtil.verifyToken(accessToken);
        System.out.println(decodedJWT);
    }

    @Test
    void testGetTokenId() {
        DecodedJWT decode = jwtUtil.decode(accessToken);
        System.out.println(decode);
        String tokenId = jwtUtil.getTokenId(decode);
        System.out.println(tokenId);
    }

    @Test
    void testGetUserId() {

        Long userId = jwtUtil.getUserId(jwtUtil.decode(accessToken));
        System.out.println(userId);
    }

    @Test
    void testVerifyRefreshToken() {
        jwtUtil.verifyToken(refreshToken);
    }
}