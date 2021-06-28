package dev.feryadi.backend.bayu.exception;

public class TokenIdNotMatchException extends RuntimeException {
    public TokenIdNotMatchException(String message) {
        super(message);
    }
}
