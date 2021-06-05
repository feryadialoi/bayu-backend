package dev.feryadi.backend.bayu.service;

import dev.feryadi.backend.bayu.model.request.LogTransactionRequest;

public interface TransactionLogService {
    void logTransactionSuccess(LogTransactionRequest logTransactionRequest);
    void logTransactionFailed(LogTransactionRequest logTransactionRequest);
}
