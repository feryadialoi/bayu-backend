package dev.feryadi.backend.bayu.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthenticationTokenNotFoundException extends AuthenticationException {
    public AuthenticationTokenNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public AuthenticationTokenNotFoundException(String msg) {
        super(msg);
    }
}
