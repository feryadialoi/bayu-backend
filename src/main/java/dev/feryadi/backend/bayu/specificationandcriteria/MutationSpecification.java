package dev.feryadi.backend.bayu.specificationandcriteria;

import dev.feryadi.backend.bayu.entity.Mutation;
import dev.feryadi.backend.bayu.entity.Wallet;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class MutationSpecification {
    public static Specification<Mutation> originWalletEqualToWallet(Wallet wallet) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(
                root.get("originWallet"),
                wallet
        );
    }
    public static Specification<Mutation> destinationWalletEqualToWallet(Wallet wallet) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(
                root.get("destinationWallet"),
                wallet
        );
    }

    public static Specification<Mutation> originWalletAndDestinationWalletEqualToWallet(Wallet wallet) {
        return originWalletEqualToWallet(wallet).and(destinationWalletEqualToWallet(wallet));
    }

    public static Specification<Mutation> originWalletOrDestinationWalletEqualToWallet(Wallet wallet) {
        return originWalletEqualToWallet(wallet).or(destinationWalletEqualToWallet(wallet));
    }

}
