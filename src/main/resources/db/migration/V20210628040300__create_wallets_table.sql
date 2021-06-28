CREATE TABLE wallets
(
    id                 BIGINT UNSIGNED AUTO_INCREMENT,
    user_id            BIGINT UNSIGNED UNIQUE,
    address            VARCHAR(255) UNIQUE,
    created_date       TIMESTAMP,
    created_by         BIGINT UNSIGNED,
    last_modified_date TIMESTAMP,
    last_modified_by   BIGINT UNSIGNED,
    deleted            BOOLEAN,
    PRIMARY KEY (id)
)