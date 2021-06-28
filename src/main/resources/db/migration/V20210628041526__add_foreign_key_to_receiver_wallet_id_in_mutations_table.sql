ALTER TABLE mutations
    ADD CONSTRAINT fk_receiver_wallet_id_mutations FOREIGN KEY (receiver_wallet_id) REFERENCES wallets (id);