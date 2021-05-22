package dev.feryadi.backend.bayu.commandimpl.usertransaction;

import dev.feryadi.backend.bayu.command.usertransaction.GetUserTransactionsCommand;
import dev.feryadi.backend.bayu.entity.Transaction;
import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.exception.NotFoundException;
import dev.feryadi.backend.bayu.model.request.ListUserTransactionRequest;
import dev.feryadi.backend.bayu.model.request.command.GetUserTransactionsCommandRequest;
import dev.feryadi.backend.bayu.model.response.UserTransactionResponse;
import dev.feryadi.backend.bayu.modelmapper.UserTransactionMapper;
import dev.feryadi.backend.bayu.repository.TransactionRepository;
import dev.feryadi.backend.bayu.repository.UserRepository;
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
    public List<UserTransactionResponse> execute(GetUserTransactionsCommandRequest request) throws Exception {
        return userRepository.findById(request.getUserId())
                .map(user -> {
                    ListUserTransactionRequest listUserTransactionRequest = request.getListUserTransactionRequest();

                    PageRequest pageRequest = PageRequest.of(
                            listUserTransactionRequest.getPage(),
                            listUserTransactionRequest.getSize(),
                            Sort.by(Sort.Direction.DESC, "createdDate")
                    );

                    Page<Transaction> page = transactionRepository.findAll(pageRequest);

                    List<Transaction> transactions = page.get().collect(Collectors.toList());

                    return transactions.stream().map(userTransactionMapper::mapTransactionToUserTransactionResponse).collect(Collectors.toList());
                })
                .orElseThrow(() -> new NotFoundException("user with id " + request.getUserId() + " not found"));

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
