package dev.feryadi.backend.bayu.modelmapperimpl;

import dev.feryadi.backend.bayu.entity.Transaction;
import dev.feryadi.backend.bayu.model.response.TransactionResponse;
import dev.feryadi.backend.bayu.modelmapper.TransactionMapper;
import dev.feryadi.backend.bayu.modelmapper.WalletMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TransactionMapperImpl implements TransactionMapper {

    private final WalletMapper walletMapper;

    @Override
    public TransactionResponse mapTransactionToTransactionResponse(Transaction transaction) {
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setId(transaction.getId());
        transactionResponse.setWallet(walletMapper.mapWalletToWalletResponse(transaction.getWallet()));
        transactionResponse.setAmount(transaction.getAmount());

        return transactionResponse;
    }
}
