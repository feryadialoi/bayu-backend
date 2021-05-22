package dev.feryadi.backend.bayu.commandimpl.usertransaction;

import dev.feryadi.backend.bayu.command.usertransaction.GetUserTransactionCommand;
import dev.feryadi.backend.bayu.entity.Transaction;
import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.exception.NotFoundException;
import dev.feryadi.backend.bayu.model.request.command.GetUserTransactionCommandRequest;
import dev.feryadi.backend.bayu.model.response.UserTransactionResponse;
import dev.feryadi.backend.bayu.modelmapper.UserTransactionMapper;
import dev.feryadi.backend.bayu.repository.TransactionRepository;
import dev.feryadi.backend.bayu.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class GetUserTransactionCommandImpl implements GetUserTransactionCommand {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final UserTransactionMapper userTransactionMapper;


    @Override
    public UserTransactionResponse execute(GetUserTransactionCommandRequest request) throws Exception {
        Optional<User> optionalUser = userRepository.findById(request.getUserId());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // todo check is transaction owned by specified user

            Optional<Transaction> optionalTransaction = transactionRepository.findById(request.getTransactionId());
            if (optionalTransaction.isPresent()) {
                Transaction transaction = optionalTransaction.get();
                return userTransactionMapper.mapTransactionToUserTransactionResponse(transaction);
            }

            throw new NotFoundException("transaction with id " + request.getTransactionId() + " not found");

        }

        throw new NotFoundException("user with id " + request.getUserId() + " not found");
    }
}
