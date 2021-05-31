ALTER TABLE transaction_logs
    MODIFY COLUMN destination_wallet_address VARCHAR(255) AFTER amount,
    MODIFY COLUMN origin_wallet_address      VARCHAR(255) AFTER amount;
