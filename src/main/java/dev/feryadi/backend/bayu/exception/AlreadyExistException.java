package dev.feryadi.backend.bayu.exception;


public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException() {
        super();
    }

    public AlreadyExistException(String message) {
        super(message);
    }

    public AlreadyExistException(Throwable throwable) {
        super(throwable);
    }

    public AlreadyExistException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public AlreadyExistException(
            String message,
            Throwable throwable,
            boolean enableSuppression,
            boolean writeableStackTrace
    ) {
        super(message, throwable, enableSuppression, writeableStackTrace);
    }
}
