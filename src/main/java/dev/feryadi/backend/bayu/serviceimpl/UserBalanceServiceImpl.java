package dev.feryadi.backend.bayu.serviceimpl;

import dev.feryadi.backend.bayu.command.userbalance.GetUserBalanceCommand;
import dev.feryadi.backend.bayu.model.request.command.GetUserBalanceCommandRequest;
import dev.feryadi.backend.bayu.model.response.BalanceResponse;
import dev.feryadi.backend.bayu.service.ServiceExecutor;
import dev.feryadi.backend.bayu.service.UserBalanceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserBalanceServiceImpl implements UserBalanceService {

    private final ServiceExecutor serviceExecutor;


    @Override
    public BalanceResponse getUserBalance(Long userId) {
        return serviceExecutor.execute(GetUserBalanceCommand.class, new GetUserBalanceCommandRequest(userId));
    }
}
