package dev.feryadi.backend.bayu.runner.commandlinerunner;

import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.entity.roleandpermission.Role;
import dev.feryadi.backend.bayu.repository.RoleRepository;
import dev.feryadi.backend.bayu.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class SeedAdminUserData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        seedAdminUserData();
    }

    private void seedAdminUserData() {
        Optional<User> admin = userRepository.findByUsername("admin");
        if (admin.isPresent()) {
            log.info("admin user already exist");
        } else {
            Optional<Role> roleAdmin = roleRepository.findRoleByName("ADMIN");
            if (roleAdmin.isPresent()) {
                User user = new User();
                user.setName("Admin");
                user.setUsername("admin");
                user.setEmail("admin@bayu.com");
                user.setPassword(passwordEncoder.encode("admin"));
                user.setRole(roleAdmin.get());

                userRepository.save(user);

                log.info("admin user created successfully");
            }
        }
    }
}
