package dev.feryadi.backend.bayu.controller;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import dev.feryadi.backend.bayu.exception.*;
import dev.feryadi.backend.bayu.model.response.ApiResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthErrorController extends ErrorBaseController {


    @ExceptionHandler(RefreshTokenExpiredException.class)
    public ResponseEntity<ApiResponseError<String>> refreshTokenExpired(RefreshTokenExpiredException refreshTokenExpiredException) {
        return createResponse(
                HttpStatus.UNAUTHORIZED,
                refreshTokenExpiredException.getMessage()
        );
    }

    @ExceptionHandler(LoginFailedException.class)
    public ResponseEntity<ApiResponseError<String>> loginFailed(LoginFailedException loginFailedException) {
        return createResponse(
                HttpStatus.UNAUTHORIZED,
                loginFailedException.getMessage()
        );
    }

    @ExceptionHandler(value = {JWTDecodeException.class})
    public ResponseEntity<ApiResponseError<String>> jwtDecode(JWTDecodeException jwtDecodeException) {
        return createResponse(
                HttpStatus.UNAUTHORIZED,
                jwtDecodeException.getMessage()
        );
    }

    @ExceptionHandler(value = {TokenNotContainUsernameException.class})
    public ResponseEntity<ApiResponseError<String>> tokenNotContainUsername(TokenNotContainUsernameException tokenNotContainUsernameException) {
        return createResponse(
                HttpStatus.UNAUTHORIZED,
                tokenNotContainUsernameException.getMessage()
        );
    }

    @ExceptionHandler(value = {TokenIdNotMatchException.class})
    public ResponseEntity<ApiResponseError<String>> tokenIdNotMatch(TokenIdNotMatchException tokenIdNotMatchException) {
        return createResponse(
                HttpStatus.UNAUTHORIZED,
                tokenIdNotMatchException.getMessage()
        );
    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    public ResponseEntity<ApiResponseError<String>> badCredentials(
            BadCredentialsException badCredentialsException
    ) {
        return createResponse(
                HttpStatus.UNAUTHORIZED,
                badCredentialsException.getMessage()
        );
    }

    @ExceptionHandler(value = {UsernameNotFoundException.class})
    public ResponseEntity<ApiResponseError<String>> usernameNotFound(
            UsernameNotFoundException usernameNotFoundException
    ) {
        return createResponse(
                HttpStatus.UNAUTHORIZED,
                usernameNotFoundException.getMessage()
        );
    }

    @ExceptionHandler(value = {InsufficientAuthenticationException.class})
    public ResponseEntity<ApiResponseError<String>> insufficientAuthentication(
            InsufficientAuthenticationException insufficientAuthenticationException
    ) {
        return createResponse(
                HttpStatus.UNAUTHORIZED,
                insufficientAuthenticationException.getMessage()
        );
    }

    @ExceptionHandler(value = {TokenExpiredException.class})
    public ResponseEntity<ApiResponseError<String>> tokenExpired(
            TokenExpiredException tokenExpiredException
    ) {
        return createResponse(
                HttpStatus.UNAUTHORIZED,
                tokenExpiredException.getMessage()
        );
    }

    @ExceptionHandler(value = {AuthenticationTokenNotFoundException.class})
    public ResponseEntity<ApiResponseError<String>> authenticationTokenNotFound(
            AuthenticationTokenNotFoundException authenticationTokenNotFoundException
    ) {
        return createResponse(
                HttpStatus.UNAUTHORIZED,
                authenticationTokenNotFoundException.getMessage()
        );
    }

    @ExceptionHandler(value = {InternalAuthenticationServiceException.class})
    public ResponseEntity<ApiResponseError<String>> internalAuthenticationService(
            InternalAuthenticationServiceException internalAuthenticationServiceException
    ) {
        return createResponse(
                HttpStatus.UNAUTHORIZED,
                internalAuthenticationServiceException.getMessage()
        );
    }
}
