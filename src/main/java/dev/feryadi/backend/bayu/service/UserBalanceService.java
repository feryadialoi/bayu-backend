package dev.feryadi.backend.bayu.service;

import dev.feryadi.backend.bayu.exception.NotFoundException;
import dev.feryadi.backend.bayu.model.response.BalanceResponse;

public interface UserBalanceService {
    BalanceResponse getUserBalance(Long userId);
}
