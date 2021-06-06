package dev.feryadi.backend.bayu.specificationandcriteria.specification;

import dev.feryadi.backend.bayu.entity.Mutation;
import dev.feryadi.backend.bayu.entity.Wallet;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class MutationSpecification {
    public static Specification<Mutation> originWalletIs(Wallet wallet) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(
                root.get("originWallet"),
                wallet
        );
    }

    public static Specification<Mutation> destinationWalletIs(Wallet wallet) {
        return (root, criteriaQuery, criteriaBuilder) -> null;
    }

    public static Specification<Mutation> typeIs(Mutation.MutationType type) {
        return (root, criteriaQuery, criteriaBuilder) -> null;
    }

}
