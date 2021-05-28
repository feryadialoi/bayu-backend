package dev.feryadi.backend.bayu.commandimpl.transaction;

import dev.feryadi.backend.bayu.command.transaction.GetTransactionsCommand;
import dev.feryadi.backend.bayu.entity.Transaction;
import dev.feryadi.backend.bayu.model.request.command.GetTransactionsCommandRequest;
import dev.feryadi.backend.bayu.model.response.TransactionResponse;
import dev.feryadi.backend.bayu.modelmapper.TransactionMapper;
import dev.feryadi.backend.bayu.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class GetTransactionsCommandImpl implements GetTransactionsCommand {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public List<TransactionResponse> execute(GetTransactionsCommandRequest request) {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream().map(transactionMapper::mapTransactionToTransactionResponse).collect(Collectors.toList());
    }
}
