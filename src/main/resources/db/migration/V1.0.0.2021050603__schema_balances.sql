CREATE TABLE balances
(
    id                 BIGINT UNSIGNED AUTO_INCREMENT,
    wallet_id          BIGINT UNSIGNED,
    amount             DECIMAL(19, 2),
    created_date       TIMESTAMP,
    created_by         BIGINT UNSIGNED,
    last_modified_date TIMESTAMP,
    last_modified_by   BIGINT UNSIGNED,
    deleted            BOOLEAN,
    PRIMARY KEY (id)
);