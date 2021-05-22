package dev.feryadi.backend.bayu.modelmapper;

import dev.feryadi.backend.bayu.entity.Transaction;
import dev.feryadi.backend.bayu.model.response.UserTransactionResponse;

public interface UserTransactionMapper {
    UserTransactionResponse mapTransactionToUserTransactionResponse(Transaction transaction);
}
