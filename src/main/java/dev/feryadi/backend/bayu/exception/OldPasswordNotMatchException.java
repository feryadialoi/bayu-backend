package dev.feryadi.backend.bayu.exception;

public class OldPasswordNotMatchException extends RuntimeException {
    public OldPasswordNotMatchException(String message) {
        super(message);
    }
}
