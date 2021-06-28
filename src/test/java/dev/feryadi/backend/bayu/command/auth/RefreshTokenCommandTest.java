package dev.feryadi.backend.bayu.command.auth;

import dev.feryadi.backend.bayu.modelmapperimpl.RefreshTokenMapperImpl;
import dev.feryadi.backend.bayu.security.JwtUtil;
import dev.feryadi.backend.bayu.commandimpl.auth.RefreshTokenCommandImpl;
import dev.feryadi.backend.bayu.exception.RefreshTokenExpiredException;
import dev.feryadi.backend.bayu.model.request.RefreshTokenRequest;
import dev.feryadi.backend.bayu.model.request.command.RefreshTokenCommandRequest;
import dev.feryadi.backend.bayu.model.response.RefreshTokenResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class RefreshTokenCommandTest {

    RefreshTokenCommand refreshTokenCommand = new RefreshTokenCommandImpl(new JwtUtil(), new RefreshTokenMapperImpl());

    @Test
    void testRefreshTokenSuccess() {
        String refreshToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJ0b2tlbklkIjoiMWVhNmYyOGYtMjAyNC00ZDA0LTg0N2EtYzcwMzQ1Y2M4OTg2IiwiZXhwIjoxNjI3MTE3OTc1LCJpYXQiOjE2MjQ1MjU5NzV9.L61ks2Y8ZVgW0pzVOR24UuwM-7wxSXNCQ04YGZ3oH8zv1lNfBZFsGkHC2R2wolC0YY0IB5Tdvtz5B4hBEUmJGA";
        String accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmZXJ5YWRpYWxvaSIsInRva2VuSWQiOiIxZWE2ZjI4Zi0yMDI0LTRkMDQtODQ3YS1jNzAzNDVjYzg5ODYiLCJleHAiOjE2MjQ1Mjk1NzUsImlhdCI6MTYyNDUyNTk3NSwidXNlcklkIjoxfQ.MOEhJUcPI5dynY9uFJf7j3nTZ0QOMv5V-vqVoTIHbEY53byh6TjADtAxTI5gu9V76NDIr9uwUUbQa315q-NL2w";


        RefreshTokenResponse refreshTokenResponse = refreshTokenCommand.execute(new RefreshTokenCommandRequest(
                new RefreshTokenRequest(
                        accessToken,
                        refreshToken
                )
        ));

        System.out.println(refreshTokenResponse);
    }

    @Test
    void testRefreshTokenFail() {
        String accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmZXJ5YWRpYWxvaSIsInRva2VuSWQiOiJjYjBiNjMyZS1lZWI5LTQ2NGItYWI4OS1mOTQwOTYwM2MzNDkiLCJleHAiOjE2MjQ1Mjg2OTYsImlhdCI6MTYyNDUyNTA5NiwidXNlcklkIjoxfQ.HQqwOxFVsNiag4rRg9sdqrNuwRDK35-8zCq9arQuDHPUkStIItQplaU64BkaKtE0bV5IozBFNGgLU03rDcWNIg";
        String refreshToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJ0b2tlbklkIjoiY2IwYjYzMmUtZWViOS00NjRiLWFiODktZjk0MDk2MDNjMzQ5IiwiZXhwIjoxNjI0NTI1MTU2LCJpYXQiOjE2MjQ1MjUwOTZ9.KLIhtWLm0RxdkZC0ceL_-rTMnXgnSot1gaqd73wZ7iJOnt1IKQP3Ubpd8RLIGnfcIDPIGyPsxhZCGOpsB4sY6w";

        Assertions.assertThrows(RefreshTokenExpiredException.class, new Executable() {
                    @Override
                    public void execute() throws Throwable {

                        RefreshTokenResponse refreshTokenResponse = refreshTokenCommand.execute(new RefreshTokenCommandRequest(
                                new RefreshTokenRequest(
                                        accessToken,
                                        refreshToken
                                )
                        ));
                    }
                }
        );
    }

}