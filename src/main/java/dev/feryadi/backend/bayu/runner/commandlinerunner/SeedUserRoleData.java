package dev.feryadi.backend.bayu.runner.commandlinerunner;

import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.entity.roleandpermission.Role;
import dev.feryadi.backend.bayu.repository.RoleRepository;
import dev.feryadi.backend.bayu.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Order(value = 4)
@Slf4j
@Transactional
@Component
@AllArgsConstructor
public class SeedUserRoleData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @Override
    public void run(String... args) throws Exception {
        seedUserRoleData();
    }

    private void seedUserRoleData() {
        Optional<User> optionalUser = userRepository.findByUsername("feryadialoi");
        Optional<Role> optionalRole = roleRepository.findRoleByName("USER");
        if (optionalUser.isPresent() && optionalRole.isPresent()) {
            User user = optionalUser.get();
            Role role = optionalRole.get();

            if (user.getRole() == null) {
                log.info("user id=" + user.getId());
                log.info("role id=" + role.getId());
                user.setRole(role);

                log.info("role added to user");
            } else {
                log.info("user already have role");
            }

        } else {
            if (!optionalUser.isPresent()) {
                log.info("no user present");
            }
            if (!optionalRole.isPresent()) {
                log.info("no role present");
            }
        }
    }
}
