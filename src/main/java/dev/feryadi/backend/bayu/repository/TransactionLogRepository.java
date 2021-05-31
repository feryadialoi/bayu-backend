package dev.feryadi.backend.bayu.repository;

import dev.feryadi.backend.bayu.entity.TransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionLogRepository extends JpaRepository<TransactionLog, Long> {
}
