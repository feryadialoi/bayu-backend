CREATE TABLE mutations
(
    id                 BIGINT UNSIGNED AUTO_INCREMENT,
    sender_wallet_id   BIGINT UNSIGNED,
    receiver_wallet_id BIGINT UNSIGNED,
    amount             DECIMAL(19, 2),
    initial_balance    DECIMAL(19, 2),
    balance            DECIMAL(19, 2),
    type               ENUM ('DEBIT','CREDIT'),
    description        TEXT,
    created_date       TIMESTAMP,
    created_by         BIGINT UNSIGNED,
    last_modified_date TIMESTAMP,
    last_modified_by   BIGINT UNSIGNED,
    deleted            BOOLEAN,
    PRIMARY KEY (id)
);