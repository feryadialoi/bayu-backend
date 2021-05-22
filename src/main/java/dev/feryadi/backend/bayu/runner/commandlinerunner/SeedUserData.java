package dev.feryadi.backend.bayu.runner.commandlinerunner;

import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;


@Order(value = 1)
@Transactional
@Component
@AllArgsConstructor
public class SeedUserData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        seedUserData();
    }

    private void seedUserData() {
        if (userRepository.count() == 0) {
            User user = new User();
            user.setName("Feryadi");
            user.setEmail("feryadialoi@gmail.com");
            user.setPhone("081254798560");
            user.setUsername("feryadialoi");
            user.setPassword(passwordEncoder.encode("password"));
            userRepository.save(user);
        }
    }
}
