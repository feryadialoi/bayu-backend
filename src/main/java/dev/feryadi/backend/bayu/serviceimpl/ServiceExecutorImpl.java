package dev.feryadi.backend.bayu.serviceimpl;

import dev.feryadi.backend.bayu.command.Command;
import dev.feryadi.backend.bayu.model.request.command.CommandRequest;
import dev.feryadi.backend.bayu.service.ServiceExecutor;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServiceExecutorImpl implements ServiceExecutor {

    private final ApplicationContext applicationContext;

    @Override
    public <RESPONSE, REQUEST extends CommandRequest> RESPONSE execute(
            Class<? extends Command<RESPONSE, REQUEST>> commandClass,
            REQUEST request) {
        return applicationContext.getBean(commandClass).execute(request);
    }
}
