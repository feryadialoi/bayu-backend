package dev.feryadi.backend.bayu.serviceimpl;

import dev.feryadi.backend.bayu.command.transaction.GetTransactionsCommand;
import dev.feryadi.backend.bayu.model.request.command.GetTransactionsCommandRequest;
import dev.feryadi.backend.bayu.model.response.TransactionResponse;
import dev.feryadi.backend.bayu.service.ServiceExecutor;
import dev.feryadi.backend.bayu.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final ServiceExecutor serviceExecutor;


    @Override
    public List<TransactionResponse> getTransactions() throws Exception {
        return serviceExecutor.execute(GetTransactionsCommand.class, new GetTransactionsCommandRequest());
    }
}
