package dev.feryadi.backend.bayu.repositoryimpl;

import dev.feryadi.backend.bayu.repositorycustom.UserRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {
    private EntityManager entityManager;


}
