ALTER TABLE mutations
    ADD CONSTRAINT fk_origin_wallet_id_mutations FOREIGN KEY (origin_wallet_id) REFERENCES wallets(id),
    ADD CONSTRAINT fk_destination_wallet_id_mutations FOREIGN KEY (destination_wallet_id) REFERENCES wallets(id);