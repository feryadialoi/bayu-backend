CREATE TABLE roles_permissions
(
    role_id       BIGINT UNSIGNED,
    permission_id BIGINT UNSIGNED,
    PRIMARY KEY (role_id, permission_id)
);