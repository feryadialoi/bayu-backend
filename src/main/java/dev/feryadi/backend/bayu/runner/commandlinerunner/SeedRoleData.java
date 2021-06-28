package dev.feryadi.backend.bayu.runner.commandlinerunner;

import dev.feryadi.backend.bayu.entity.roleandpermission.Role;
import dev.feryadi.backend.bayu.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Order(value = 2)
@Transactional
@Component
@AllArgsConstructor
public class SeedRoleData implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        seedRoleData();
    }

    private void seedRoleData() {
        if (roleRepository.count() == 0) {

            List<Role> roles = Arrays.asList(
                    Role.builder().name("ADMIN").build(),
                    Role.builder().name("USER").build()
            );

            roleRepository.saveAll(roles);
        }

    }
}
