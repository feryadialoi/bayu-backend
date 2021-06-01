package dev.feryadi.backend.bayu.exception;

public class PinNotFoundException extends RuntimeException {
    public PinNotFoundException(String message) {
        super(message);
    }
}
