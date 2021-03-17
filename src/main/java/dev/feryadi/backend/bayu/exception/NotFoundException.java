package dev.feryadi.backend.bayu.exception;

public class NotFoundException extends Exception{
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(){
        super();
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
