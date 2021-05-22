package dev.feryadi.backend.bayu.controller;

import com.auth0.jwt.exceptions.TokenExpiredException;
import dev.feryadi.backend.bayu.exception.AuthenticationTokenNotFoundException;
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
public class AuthenticationErrorController extends ErrorBaseController {
    @ExceptionHandler(value = {BadCredentialsException.class})
    public ResponseEntity<ApiResponseError<String>> badCredentials(
            BadCredentialsException badCredentialsException
    ) {
        return createResponse(HttpStatus.UNAUTHORIZED, "", badCredentialsException.getMessage());
    }

    @ExceptionHandler(value = {UsernameNotFoundException.class})
    public ResponseEntity<ApiResponseError<String>> usernameNotFound(
            UsernameNotFoundException usernameNotFoundException
    ) {
        return createResponse(HttpStatus.UNAUTHORIZED, "", usernameNotFoundException.getMessage());
    }

    @ExceptionHandler(value = {InsufficientAuthenticationException.class})
    public ResponseEntity<ApiResponseError<String>> insufficientAuthentication(
            InsufficientAuthenticationException insufficientAuthenticationException
    ) {
        return createResponse(HttpStatus.UNAUTHORIZED, "", insufficientAuthenticationException.getMessage());
    }

    @ExceptionHandler(value = {TokenExpiredException.class})
    public ResponseEntity<ApiResponseError<String>> tokenExpired(
            TokenExpiredException tokenExpiredException
    ) {
        return createResponse(HttpStatus.UNAUTHORIZED, "", tokenExpiredException.getMessage());
    }

    @ExceptionHandler(value = {AuthenticationTokenNotFoundException.class})
    public ResponseEntity<ApiResponseError<String>> authenticationTokenNotFound(
            AuthenticationTokenNotFoundException authenticationTokenNotFoundException
    ) {
        return createResponse(HttpStatus.UNAUTHORIZED, "", authenticationTokenNotFoundException.getMessage());
    }

    @ExceptionHandler(value = {InternalAuthenticationServiceException.class})
    public ResponseEntity<ApiResponseError<String>> internalAuthenticationService(
            InternalAuthenticationServiceException internalAuthenticationServiceException
    ) {
        return createResponse(HttpStatus.UNAUTHORIZED, "", internalAuthenticationServiceException.getMessage());
    }
}
