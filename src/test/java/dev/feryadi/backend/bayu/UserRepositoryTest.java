package dev.feryadi.backend.bayu;

import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void getUserTest() {
        Optional<User> user = userRepository.findById(1L);
    }
}
