package dev.feryadi.backend.bayu.repository;

import dev.feryadi.backend.bayu.entity.Mutation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MutationRepository extends JpaRepository<Mutation, Long>, JpaSpecificationExecutor<Mutation> {
}
