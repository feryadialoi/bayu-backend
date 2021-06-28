ALTER TABLE mutations
    ADD CONSTRAINT fk_sender_wallet_id_mutations FOREIGN KEY (sender_wallet_id) REFERENCES wallets (id);