package dev.feryadi.backend.bayu.repository;

import dev.feryadi.backend.bayu.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
