package dev.feryadi.backend.bayu.runner.commandlinerunner;

import dev.feryadi.backend.bayu.entity.roleandpermission.Permission;
import dev.feryadi.backend.bayu.repository.PermissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

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

    private void seedPermissionData(){
        if(permissionRepository.count() == 0) {
            Permission getTransaction = new Permission();
            getTransaction.setName("GET_TRANSACTION");

            Permission getTransactions = new Permission();
            getTransactions.setName("GET_TRANSACTIONS");

            Permission updateTransactions = new Permission();
            updateTransactions.setName("UPDATE_TRANSACTION");

            Permission createTransaction = new Permission();
            createTransaction.setName("CREATE_TRANSACTION");

            Permission deleteTransaction = new Permission();
            deleteTransaction.setName("DELETE_TRANSACTION");


            permissionRepository.save(getTransaction);
            permissionRepository.save(getTransactions);
            permissionRepository.save(createTransaction);
            permissionRepository.save(updateTransactions);
            permissionRepository.save(deleteTransaction);
        }
    }
}
