CREATE TABLE transaction_logs
(
    id                    BIGINT UNSIGNED AUTO_INCREMENT,
    destination_wallet_id BIGINT UNSIGNED,
    origin_wallet_id      BIGINT UNSIGNED,
    amount                DECIMAL(19, 2),
    description           TEXT,
    created_date          TIMESTAMP,
    created_by            BIGINT UNSIGNED,
    last_modified_date    TIMESTAMP,
    last_modified_by      BIGINT UNSIGNED,
    deleted               BOOLEAN,
    PRIMARY KEY (id)
);