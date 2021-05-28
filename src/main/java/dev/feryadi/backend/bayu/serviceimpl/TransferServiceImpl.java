package dev.feryadi.backend.bayu.serviceimpl;

import dev.feryadi.backend.bayu.command.transfer.TransferCommand;
import dev.feryadi.backend.bayu.model.request.TransferRequest;
import dev.feryadi.backend.bayu.model.request.command.TransferCommandRequest;
import dev.feryadi.backend.bayu.model.response.TransferResponse;
import dev.feryadi.backend.bayu.service.ServiceExecutor;
import dev.feryadi.backend.bayu.service.TransferService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final ServiceExecutor serviceExecutor;


    @Override
    public TransferResponse transfer(TransferRequest transferRequest) {
        return serviceExecutor.execute(
                TransferCommand.class,
                new TransferCommandRequest(transferRequest)
        );
    }
}
