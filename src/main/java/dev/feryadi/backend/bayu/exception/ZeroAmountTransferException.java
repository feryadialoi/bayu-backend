package dev.feryadi.backend.bayu.exception;

public class ZeroAmountTransferException extends RuntimeException {
    public ZeroAmountTransferException(String message) {
        super(message);
    }
}
