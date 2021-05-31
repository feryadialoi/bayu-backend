ALTER TABLE transaction_logs
    DROP COLUMN origin_wallet_id,
    DROP COLUMN destination_wallet_id,
    ADD COLUMN origin_wallet_address      VARCHAR(255),
    ADD COLUMN destination_wallet_address VARCHAR(255);
