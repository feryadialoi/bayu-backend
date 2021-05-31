package dev.feryadi.backend.bayu.repository;

import dev.feryadi.backend.bayu.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        User user = User.builder()
                .id(10L)
                .name("aloi")
                .email("aloi@gmail.com")
                .username("aloi")
                .password("password")
                .phone("081254798560")
                .build();

        entityManager.merge(user);
    }

    @Test
    public void whenFindByUsername_thenReturnUser() {
        User user = userRepository.findByUsername("aloi").get();
        assertEquals(user.getName(), "aloi");
    }

    @Test
    void findByEmail() {
    }

    @Test
    void findByUsername() {
    }

    @Test
    void findAllByRoleName() {
    }
}