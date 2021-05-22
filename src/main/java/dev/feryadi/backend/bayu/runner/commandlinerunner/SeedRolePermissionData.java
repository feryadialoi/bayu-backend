package dev.feryadi.backend.bayu.runner.commandlinerunner;

import dev.feryadi.backend.bayu.entity.roleandpermission.Permission;
import dev.feryadi.backend.bayu.entity.roleandpermission.Role;
import dev.feryadi.backend.bayu.repository.PermissionRepository;
import dev.feryadi.backend.bayu.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Order(value = 5)
@Slf4j
@Transactional
@Component
@AllArgsConstructor
public class SeedRolePermissionData implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;


    @Override
    public void run(String... args) throws Exception {
        seedRolePermissionData();
    }

    private void seedRolePermissionData() {
        Optional<Role> optionalRole = roleRepository.findRoleByName("USER");
        List<Permission> permissions = permissionRepository.findAll();
        if (optionalRole.isPresent() && permissions.size() > 0){
            Role role = optionalRole.get();
            role.setPermissions(permissions);
            log.info("permission added to role");
        } else {
            log.info("no role found");
        }
    }
}
