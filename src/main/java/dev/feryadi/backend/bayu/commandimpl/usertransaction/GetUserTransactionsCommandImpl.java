package dev.feryadi.backend.bayu.commandimpl.usertransaction;

import dev.feryadi.backend.bayu.command.usertransaction.GetUserTransactionsCommand;
import dev.feryadi.backend.bayu.exception.UserNotFoundException;
import dev.feryadi.backend.bayu.specificationandcriteria.SearchCriteria;
import dev.feryadi.backend.bayu.specificationandcriteria.SearchOperation;
import dev.feryadi.backend.bayu.entity.Transaction;
import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.exception.NotFoundException;
import dev.feryadi.backend.bayu.model.request.command.GetUserTransactionsCommandRequest;
import dev.feryadi.backend.bayu.model.response.UserTransactionResponse;
import dev.feryadi.backend.bayu.modelmapper.UserTransactionMapper;
import dev.feryadi.backend.bayu.repository.TransactionRepository;
import dev.feryadi.backend.bayu.repository.UserRepository;
import dev.feryadi.backend.bayu.specificationandcriteria.GenericSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

// VERSION 3
        Optional<User> optionalUser = userRepository.findById(request.getUserId());
        if (optionalUser.isPresent()) {
            // pageable
            PageRequest pageRequest = PageRequest.of(
                    request.getListUserTransactionRequest().getPage(),
                    request.getListUserTransactionRequest().getSize(),
                    Sort.by(Sort.Direction.DESC, "createdDate")
            );

            // specification
            GenericSpecification<Transaction> specification = new GenericSpecification<>();
            SearchCriteria userCriteria = SearchCriteria.builder()
                    .key("user")
                    .operation(SearchOperation.EQUAL)
                    .value(optionalUser.get())
                    .build();
            specification.add(userCriteria);

            Page<Transaction> page = transactionRepository.findAll(specification, pageRequest);

            List<Transaction> transactions = page.get().collect(Collectors.toList());

            return transactions.stream()
                    .map(userTransactionMapper::mapTransactionToUserTransactionResponse)
                    .collect(Collectors.toList());
        }

        throw new UserNotFoundException("user with id " + request.getUserId() + " not found");


// VERSION 2
//        return userRepository.findById(request.getUserId())
//                .map(user -> {
//                    ListUserTransactionRequest listUserTransactionRequest = request.getListUserTransactionRequest();
//
//
//
//                    PageRequest pageRequest = PageRequest.of(
//                            listUserTransactionRequest.getPage(),
//                            listUserTransactionRequest.getSize(),
//                            Sort.by(Sort.Direction.DESC, "createdDate")
//                    );
//
//                    Page<Transaction> page = transactionRepository.findAll(pageRequest);
//
//                    List<Transaction> transactions = page.get().collect(Collectors.toList());
//
//                    return transactions.stream().map(userTransactionMapper::mapTransactionToUserTransactionResponse).collect(Collectors.toList());
//                })
//                .orElseThrow(() -> new NotFoundException("user with id " + request.getUserId() + " not found"));

// VERSION 1
//        Long userId = request.getUserId();
//        ListUserTransactionRequest listUserTransactionRequest = request.getListUserTransactionRequest();
//
//        Optional<User> optionalUser = userRepository.findById(userId);
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//
//            PageRequest pageRequest = PageRequest.of(listUserTransactionRequest.getPage(), listUserTransactionRequest.getSize(), Sort.by(Sort.Direction.DESC, "createdDate"));
//
//            Page<Transaction> page = transactionRepository.findAll(pageRequest);
//
//            List<Transaction> transactions = page.get().collect(Collectors.toList());
//
//            return transactions.stream().map(userTransactionMapper::mapTransactionToUserTransactionResponse).collect(Collectors.toList());
//
//        }
//
//        throw new NotFoundException("user with id " + userId + " not found");
    }
}
