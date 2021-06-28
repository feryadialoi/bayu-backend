ALTER TABLE users
    ADD CONSTRAINT uk_username_users UNIQUE (username);