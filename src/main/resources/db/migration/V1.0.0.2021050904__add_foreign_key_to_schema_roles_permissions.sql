ALTER TABLE roles_permissions
    ADD CONSTRAINT fk_role_id_roles_permissions FOREIGN KEY (role_id) REFERENCES roles (id),
    ADD CONSTRAINT fk_permission_id_roles_permissions FOREIGN KEY (permission_id) REFERENCES permissions (id);