package dev.feryadi.backend.bayu.serviceimpl;

import dev.feryadi.backend.bayu.command.usertransaction.GetUserTransactionCommand;
import dev.feryadi.backend.bayu.command.usertransaction.GetUserTransactionsCommand;
import dev.feryadi.backend.bayu.entity.Transaction;
import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.entity.Wallet;
import dev.feryadi.backend.bayu.exception.NotFoundException;
import dev.feryadi.backend.bayu.model.request.ListUserTransactionRequest;
import dev.feryadi.backend.bayu.model.request.command.GetUserTransactionCommandRequest;
import dev.feryadi.backend.bayu.model.request.command.GetUserTransactionsCommandRequest;
import dev.feryadi.backend.bayu.model.response.UserTransactionResponse;
import dev.feryadi.backend.bayu.modelmapper.TransactionMapper;
import dev.feryadi.backend.bayu.modelmapper.UserTransactionMapper;
import dev.feryadi.backend.bayu.repository.TransactionRepository;
import dev.feryadi.backend.bayu.repository.UserRepository;
import dev.feryadi.backend.bayu.service.ServiceExecutor;
import dev.feryadi.backend.bayu.service.UserTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserTransactionServiceImpl implements UserTransactionService {

    private final ServiceExecutor serviceExecutor;


    @Override
    public List<UserTransactionResponse> getUserTransactions(Long userId, ListUserTransactionRequest listUserTransactionRequest) throws Exception {
        return serviceExecutor.execute(GetUserTransactionsCommand.class, new GetUserTransactionsCommandRequest(userId, listUserTransactionRequest));
    }

    @Override
    public UserTransactionResponse getUserTransaction(Long userId, Long transactionId) throws Exception {
        return serviceExecutor.execute(GetUserTransactionCommand.class, new GetUserTransactionCommandRequest(userId, transactionId));
    }
}
