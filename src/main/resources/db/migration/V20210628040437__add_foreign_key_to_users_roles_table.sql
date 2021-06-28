ALTER TABLE users_roles
    ADD CONSTRAINT fk_user_id_users_roles FOREIGN KEY (user_id) REFERENCES users (id),
    ADD CONSTRAINT fk_role_id_users_roles FOREIGN KEY (role_id) REFERENCES roles (id);