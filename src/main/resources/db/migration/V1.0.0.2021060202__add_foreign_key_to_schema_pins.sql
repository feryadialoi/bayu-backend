ALTER TABLE pins
    ADD CONSTRAINT fk_user_id_pins FOREIGN KEY (user_id) REFERENCES users (id);