ALTER TABLE balances
    MODIFY COLUMN initial_balance DECIMAL(19, 2) AFTER wallet_id,
    MODIFY COLUMN balance DECIMAL(19, 2) AFTER initial_balance;