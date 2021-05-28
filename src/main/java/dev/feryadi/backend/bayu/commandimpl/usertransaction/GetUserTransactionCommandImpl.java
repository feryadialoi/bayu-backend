package dev.feryadi.backend.bayu.commandimpl.usertransaction;

import dev.feryadi.backend.bayu.command.usertransaction.GetUserTransactionCommand;
import dev.feryadi.backend.bayu.exception.TransactionNotFoundException;
import dev.feryadi.backend.bayu.exception.UserNotFoundException;
import dev.feryadi.backend.bayu.specificationandcriteria.SearchCriteria;
import dev.feryadi.backend.bayu.specificationandcriteria.SearchOperation;
import dev.feryadi.backend.bayu.entity.Transaction;
import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.exception.NotFoundException;
import dev.feryadi.backend.bayu.model.request.command.GetUserTransactionCommandRequest;
import dev.feryadi.backend.bayu.model.response.UserTransactionResponse;
import dev.feryadi.backend.bayu.modelmapper.UserTransactionMapper;
import dev.feryadi.backend.bayu.repository.TransactionRepository;
import dev.feryadi.backend.bayu.repository.UserRepository;
import dev.feryadi.backend.bayu.specificationandcriteria.GenericSpecification;
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
    public UserTransactionResponse execute(GetUserTransactionCommandRequest request) {

// VERSION 3
        Optional<User> optionalUser = userRepository.findById(request.getUserId());
        if (optionalUser.isPresent()) {
            GenericSpecification<Transaction> specification = new GenericSpecification<>();

            specification.add(SearchCriteria.builder()
                    .key("user")
                    .value(SearchOperation.EQUAL)
                    .value(optionalUser.get())
                    .build());

            specification.add(SearchCriteria.builder()
                    .key("id")
                    .operation(SearchOperation.EQUAL)
                    .value(request.getTransactionId())
                    .build());

            return transactionRepository.findOne(specification)
                    .map(userTransactionMapper::mapTransactionToUserTransactionResponse)
                    .orElseThrow(() -> new TransactionNotFoundException("transaction with id " + request.getTransactionId() + " not found"));

        }

        throw new UserNotFoundException("user with id " + request.getUserId() + " not found");

// VERSION 2
//        return userRepository.findById(request.getUserId())
//                .flatMap(user -> user.getWallet().getTransactions()
//                        .stream()
//                        .filter(transaction -> transaction.getId().equals(request.getTransactionId()))
//                        .findFirst())
//                .map(userTransactionMapper::mapTransactionToUserTransactionResponse)
//                .orElseThrow(() -> new NotFoundException("transaction with id " + request.getTransactionId() + " not found"));


// VERSION 1
//        Optional<User> optionalUser = userRepository.findById(request.getUserId());
//
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//
//
//
//            Optional<Transaction> optionalTransaction = transactionRepository.findById(request.getTransactionId());
//            if (optionalTransaction.isPresent()) {
//                Transaction transaction = optionalTransaction.get();
//
//                if(!user.getId().equals(transaction.getWallet().getUser().getId())) {
//
//                }
//
//                return userTransactionMapper.mapTransactionToUserTransactionResponse(transaction);
//            }
//
//            throw new NotFoundException("transaction with id " + request.getTransactionId() + " not found");
//
//        }
//
//        throw new NotFoundException("user with id " + request.getUserId() + " not found");
    }
}
