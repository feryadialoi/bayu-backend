package dev.feryadi.backend.bayu.exception;

public class MutationNotFoundException extends RuntimeException {
    public MutationNotFoundException(String message) {
        super(message);
    }
}
