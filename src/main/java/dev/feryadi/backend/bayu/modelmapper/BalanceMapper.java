package dev.feryadi.backend.bayu.modelmapper;

import dev.feryadi.backend.bayu.entity.Balance;
import dev.feryadi.backend.bayu.model.response.BalanceResponse;

public interface BalanceMapper {
    BalanceResponse mapBalanceToBalanceResponse(Balance balance);
}
