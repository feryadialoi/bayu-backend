package dev.feryadi.backend.bayu.specificationandcriteria.specification;

import dev.feryadi.backend.bayu.entity.Transaction;
import dev.feryadi.backend.bayu.entity.User;
import org.springframework.data.jpa.domain.Specification;

public class TransactionSpecification {
    public static Specification<Transaction> userIs(User user) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(
                root.get("user"),
                user
        );
    }
}
