ALTER TABLE mutations
    ADD COLUMN initial_balance DECIMAL(19,2) AFTER amount,
    ADD COLUMN balance DECIMAL(19,2) AFTER initial_balance,
    ADD COLUMN type ENUM('DEBIT','CREDIT') AFTER balance;