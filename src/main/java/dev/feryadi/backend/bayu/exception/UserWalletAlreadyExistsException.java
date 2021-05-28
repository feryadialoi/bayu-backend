package dev.feryadi.backend.bayu.exception;

public class UserWalletAlreadyExistsException extends RuntimeException {
    public UserWalletAlreadyExistsException(String message) {
        super(message);
    }
}
