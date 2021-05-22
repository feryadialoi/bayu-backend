ALTER TABLE balances
    DROP COLUMN amount,
    ADD COLUMN balance DECIMAL(19, 2);