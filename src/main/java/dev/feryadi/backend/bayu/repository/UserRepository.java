package dev.feryadi.backend.bayu.repository;

import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.model.request.GetUserByEmail;
import dev.feryadi.backend.bayu.repositorycustom.UserRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>, UserRepositoryCustom {
//    @Transactional
//    @Query("select u from User u where u.email = :#{#getUserByEmail.email}")
//    User getUserByEmail(@Param("getUserByEmail") GetUserByEmail getUserByEmail);

//    @Transactional
//    @Query("select u from User u where u.username = :username")
//    User getUserByUsername(@Param("username") String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    Page<User> findAllByRoleName(String roleName, Pageable pageable);
}
