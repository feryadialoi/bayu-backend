package dev.feryadi.backend.bayu.exception;

public class ForbiddenAccessException extends Exception{
    public ForbiddenAccessException() {

    }

    public ForbiddenAccessException(String message) {
        super(message);
    }
}
