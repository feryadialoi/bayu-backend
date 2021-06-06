package dev.feryadi.backend.bayu.commandimpl.usertransaction;

import dev.feryadi.backend.bayu.command.usertransaction.GetUserTransactionsCommand;
import dev.feryadi.backend.bayu.exception.UserNotFoundException;
import dev.feryadi.backend.bayu.entity.Transaction;
import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.model.request.command.GetUserTransactionsCommandRequest;
import dev.feryadi.backend.bayu.model.response.UserTransactionResponse;
import dev.feryadi.backend.bayu.modelmapper.UserTransactionMapper;
import dev.feryadi.backend.bayu.repository.TransactionRepository;
import dev.feryadi.backend.bayu.repository.UserRepository;
import dev.feryadi.backend.bayu.specificationandcriteria.specification.TransactionSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class GetUserTransactionsCommandImpl implements GetUserTransactionsCommand {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final UserTransactionMapper userTransactionMapper;


    @Override
    public List<UserTransactionResponse> execute(GetUserTransactionsCommandRequest request) {

        Optional<User> optionalUser = userRepository.findById(request.getUserId());
        if (optionalUser.isPresent()) {

            User user = optionalUser.get();
            Page<Transaction> page = transactionRepository.findAll(
                    TransactionSpecification.userIs(user),
                    request.getListUserTransactionRequest().getPageable()
            );

            List<Transaction> transactions = page.get().collect(Collectors.toList());

            return transactions.stream()
                    .map(userTransactionMapper::mapTransactionToUserTransactionResponse)
                    .collect(Collectors.toList());
        }

        throw new UserNotFoundException("user with id " + request.getUserId() + " not found");

    }
}
