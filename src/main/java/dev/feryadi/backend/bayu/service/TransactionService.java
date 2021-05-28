package dev.feryadi.backend.bayu.service;

import dev.feryadi.backend.bayu.model.response.TransactionResponse;

import java.util.List;

public interface TransactionService {
    List<TransactionResponse> getTransactions();
}
