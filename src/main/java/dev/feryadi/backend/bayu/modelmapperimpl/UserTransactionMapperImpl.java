package dev.feryadi.backend.bayu.modelmapperimpl;

import dev.feryadi.backend.bayu.entity.Transaction;
import dev.feryadi.backend.bayu.model.response.UserTransactionResponse;
import dev.feryadi.backend.bayu.modelmapper.TransactionMapper;
import dev.feryadi.backend.bayu.modelmapper.UserTransactionMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserTransactionMapperImpl implements UserTransactionMapper {

    private final TransactionMapper transactionMapper;


    @Override
    public UserTransactionResponse mapTransactionToUserTransactionResponse(Transaction transaction) {

        UserTransactionResponse userTransactionResponse = new UserTransactionResponse();
        userTransactionResponse.setTransaction(transactionMapper.mapTransactionToTransactionResponse(transaction));

        return userTransactionResponse;
    }
}
