package dev.feryadi.backend.bayu.repository;

import dev.feryadi.backend.bayu.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BalanceRepository extends JpaRepository<Balance, Long>, JpaSpecificationExecutor<Balance> {
}
