package dev.feryadi.backend.bayu.repository;

import dev.feryadi.backend.bayu.entity.roleandpermission.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
