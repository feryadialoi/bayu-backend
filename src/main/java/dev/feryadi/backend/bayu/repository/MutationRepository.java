package dev.feryadi.backend.bayu.repository;

import dev.feryadi.backend.bayu.entity.Mutation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MutationRepository extends JpaRepository<Mutation, Long> {
}
