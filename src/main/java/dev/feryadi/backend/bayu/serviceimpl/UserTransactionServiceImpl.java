package dev.feryadi.backend.bayu.serviceimpl;

import dev.feryadi.backend.bayu.command.usertransaction.GetUserTransactionCommand;
import dev.feryadi.backend.bayu.command.usertransaction.GetUserTransactionsCommand;
import dev.feryadi.backend.bayu.model.request.ListUserTransactionRequest;
import dev.feryadi.backend.bayu.model.request.command.GetUserTransactionCommandRequest;
import dev.feryadi.backend.bayu.model.request.command.GetUserTransactionsCommandRequest;
import dev.feryadi.backend.bayu.model.response.UserTransactionResponse;
import dev.feryadi.backend.bayu.service.ServiceExecutor;
import dev.feryadi.backend.bayu.service.UserTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserTransactionServiceImpl implements UserTransactionService {

    private final ServiceExecutor serviceExecutor;


    @Override
    public List<UserTransactionResponse> getUserTransactions(Long userId, ListUserTransactionRequest listUserTransactionRequest) {
        return serviceExecutor.execute(GetUserTransactionsCommand.class, new GetUserTransactionsCommandRequest(userId, listUserTransactionRequest));
    }

    @Override
    public UserTransactionResponse getUserTransaction(Long userId, Long transactionId) {
        return serviceExecutor.execute(GetUserTransactionCommand.class, new GetUserTransactionCommandRequest(userId, transactionId));
    }
}
