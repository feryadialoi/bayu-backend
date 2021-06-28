package dev.feryadi.backend.bayu.repository;

import dev.feryadi.backend.bayu.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    Page<User> findAllByRoleName(String roleName, Pageable pageable);
}
