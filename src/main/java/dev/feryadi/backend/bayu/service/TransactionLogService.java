package dev.feryadi.backend.bayu.service;

import dev.feryadi.backend.bayu.model.request.LogTransactionRequest;

public interface TransactionLogService {
    void logTransactionStart(LogTransactionRequest logTransactionRequest);
    void logTransactionSuccess(LogTransactionRequest logTransactionRequest);
}
