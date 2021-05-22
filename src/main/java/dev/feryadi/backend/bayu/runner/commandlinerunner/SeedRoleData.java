package dev.feryadi.backend.bayu.runner.commandlinerunner;

import dev.feryadi.backend.bayu.entity.roleandpermission.Role;
import dev.feryadi.backend.bayu.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

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

            Role admin = new Role();
            admin.setName("ADMIN");

            Role user = new Role();
            user.setName("USER");

            roleRepository.save(admin);
            roleRepository.save(user);
        }

    }
}
