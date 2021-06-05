package dev.feryadi.backend.bayu.exception;

public class UserAndWalletNotMatchException extends RuntimeException {
    public UserAndWalletNotMatchException(String message) {
        super(message);
    }
}
