package dev.feryadi.backend.bayu.modelmapperimpl;

import dev.feryadi.backend.bayu.entity.Balance;
import dev.feryadi.backend.bayu.model.response.BalanceResponse;
import dev.feryadi.backend.bayu.modelmapper.BalanceMapper;
import org.springframework.stereotype.Component;

@Component
public class BalanceMapperImpl implements BalanceMapper {
    @Override
    public BalanceResponse mapBalanceToBalanceResponse(Balance balance) {
        BalanceResponse balanceResponse = new BalanceResponse();
        balanceResponse.setId(balance.getId());
        balanceResponse.setInitialBalance(balance.getInitialBalance());
        balanceResponse.setBalance(balance.getBalance());

        return balanceResponse;
    }
}
