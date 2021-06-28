ALTER TABLE wallets
    ADD CONSTRAINT fk_user_id_wallets FOREIGN KEY (user_id) REFERENCES users (id);