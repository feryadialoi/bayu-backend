package dev.feryadi.backend.bayu.service;

import dev.feryadi.backend.bayu.exception.NotFoundException;
import dev.feryadi.backend.bayu.model.request.ListUserTransactionRequest;
import dev.feryadi.backend.bayu.model.response.UserTransactionResponse;

import java.util.List;

public interface UserTransactionService {
    List<UserTransactionResponse> getUserTransactions(Long userId, ListUserTransactionRequest listUserTransactionRequest);

    UserTransactionResponse getUserTransaction(Long userId, Long transactionId);
}
