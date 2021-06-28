package dev.feryadi.backend.bayu.runner.commandlinerunner;

import dev.feryadi.backend.bayu.entity.roleandpermission.Permission;
import dev.feryadi.backend.bayu.repository.PermissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Order(value = 3)
@Transactional
@Component
@AllArgsConstructor
public class SeedPermissionData implements CommandLineRunner {

    private final PermissionRepository permissionRepository;


    @Override
    public void run(String... args) throws Exception {
        seedPermissionData();
    }

    private void seedPermissionData() {
        if (permissionRepository.count() == 0) {

            List<Permission> permissions = Arrays.asList(
                    Permission.builder().name("GET_TRANSACTION").build(),
                    Permission.builder().name("GET_TRANSACTIONS").build(),
                    Permission.builder().name("UPDATE_TRANSACTION").build(),
                    Permission.builder().name("CREATE_TRANSACTION").build(),
                    Permission.builder().name("DELETE_TRANSACTION").build()
            );

            permissionRepository.saveAll(permissions);
        }
    }
}
