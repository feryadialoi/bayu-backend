package dev.feryadi.backend.bayu.modelmapper;

import dev.feryadi.backend.bayu.entity.Transaction;
import dev.feryadi.backend.bayu.model.response.TransactionResponse;

public interface TransactionMapper {
    TransactionResponse mapTransactionToTransactionResponse(Transaction transaction);
}
