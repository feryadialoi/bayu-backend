package dev.feryadi.backend.bayu.exception;

public class TokenNotContainUsernameException extends RuntimeException {
    public TokenNotContainUsernameException(String message) {
        super(message);
    }
}
