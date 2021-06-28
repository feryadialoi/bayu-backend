CREATE TABLE transaction_logs
(
    id                      BIGINT UNSIGNED AUTO_INCREMENT,
    sender_wallet_address   VARCHAR(255),
    receiver_wallet_address VARCHAR(255),
    amount                  DECIMAL(19, 2),
    description             TEXT,
    status                  enum ('SUCCESS', 'FAIL'),
    status_detail           VARCHAR(255),
    created_date            TIMESTAMP,
    created_by              BIGINT UNSIGNED,
    last_modified_date      TIMESTAMP,
    last_modified_by        BIGINT UNSIGNED,
    deleted                 BOOLEAN,
    PRIMARY KEY (id)
);