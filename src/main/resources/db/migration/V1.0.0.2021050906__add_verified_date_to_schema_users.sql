ALTER TABLE users
    ADD COLUMN verified_date TIMESTAMP AFTER password;