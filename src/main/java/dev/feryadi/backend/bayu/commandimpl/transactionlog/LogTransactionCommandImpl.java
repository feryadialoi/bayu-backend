package dev.feryadi.backend.bayu.commandimpl.transactionlog;

import dev.feryadi.backend.bayu.command.transactionlog.LogTransactionCommand;
import dev.feryadi.backend.bayu.entity.TransactionLog;
import dev.feryadi.backend.bayu.model.request.LogTransactionRequest;
import dev.feryadi.backend.bayu.model.request.command.LogTransactionCommandRequest;
import dev.feryadi.backend.bayu.model.response.LogTransactionResponse;
import dev.feryadi.backend.bayu.repository.TransactionLogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class LogTransactionCommandImpl implements LogTransactionCommand {

    private final TransactionLogRepository transactionLogRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public LogTransactionResponse execute(LogTransactionCommandRequest request) {

        TransactionLog transactionLog = TransactionLog.builder()
                .senderWalletAddress(request
                        .getLogTransactionRequest()
                        .getSenderWalletAddress()
                )
                .receiverWalletAddress(request
                        .getLogTransactionRequest()
                        .getReceiverWalletAddress()
                )
                .amount(request
                        .getLogTransactionRequest()
                        .getAmount()
                )
                .description(request
                        .getLogTransactionRequest()
                        .getDescription()
                )
                .status(
                        request.getLogTransactionRequest().getStatus() == LogTransactionRequest.Status.SUCCESS
                                ? TransactionLog.TransactionLogStatus.SUCCESS
                                : TransactionLog.TransactionLogStatus.FAIL
                )
                .statusDetail(request
                        .getLogTransactionRequest()
                        .getStatusDetail()
                )
                .build();

        transactionLog = transactionLogRepository.save(transactionLog);

        return LogTransactionResponse.builder()
                .description(transactionLog.getDescription())
                .build();
    }
}
