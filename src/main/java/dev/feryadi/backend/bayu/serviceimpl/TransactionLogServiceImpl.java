package dev.feryadi.backend.bayu.serviceimpl;

import dev.feryadi.backend.bayu.command.transactionlog.LogTransactionCommand;
import dev.feryadi.backend.bayu.model.request.LogTransactionRequest;
import dev.feryadi.backend.bayu.model.request.command.LogTransactionCommandRequest;
import dev.feryadi.backend.bayu.service.ServiceExecutor;
import dev.feryadi.backend.bayu.service.TransactionLogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionLogServiceImpl implements TransactionLogService {

    private final ServiceExecutor serviceExecutor;


    @Override
    public void logTransactionSuccess(LogTransactionRequest logTransactionRequest) {
        serviceExecutor.execute(LogTransactionCommand.class, new LogTransactionCommandRequest(logTransactionRequest));
    }

    @Override
    public void logTransactionFailed(LogTransactionRequest logTransactionRequest) {
        serviceExecutor.execute(LogTransactionCommand.class, new LogTransactionCommandRequest(logTransactionRequest));
    }
}
