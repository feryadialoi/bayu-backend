package dev.feryadi.backend.bayu.repository;

import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.specificationandcriteria.SearchCriteria;
import dev.feryadi.backend.bayu.specificationandcriteria.SearchOperation;
import dev.feryadi.backend.bayu.specificationandcriteria.UniversalSpecification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntityManager entityManager;

    @Test
    void testGetUserIn() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);


        UniversalSpecification<User> specification = UniversalSpecification.<User>builder()
                .searchCriteria(SearchCriteria.builder()
                        .key("id")
                        .operation(SearchOperation.IN)
                        .values(Arrays.asList(1, 2, 3))
                        .build())
                .build();

        UniversalSpecification<User> specification1 = new UniversalSpecification<>(new SearchCriteria("id", SearchOperation.IN, Arrays.asList(1, 2, 3)));

        List<User> all = userRepository.findAll(specification1);
        all.forEach(user -> System.out.println(MessageFormat.format("id = {0}, name = {1}", user.getId(), user.getName())));
    }
}