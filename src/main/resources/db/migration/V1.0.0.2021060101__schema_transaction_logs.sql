CREATE TABLE transaction_logs
(
    id                         BIGINT UNSIGNED AUTO_INCREMENT,
    origin_wallet_address      VARCHAR(255),
    destination_wallet_address VARCHAR(255),
    amount                     DECIMAL(19, 2),
    description                TEXT,
    created_date               TIMESTAMP,
    created_by                 BIGINT UNSIGNED,
    last_modified_date         TIMESTAMP,
    last_modified_by           BIGINT UNSIGNED,
    deleted                    BOOLEAN,
    PRIMARY KEY (id)
);