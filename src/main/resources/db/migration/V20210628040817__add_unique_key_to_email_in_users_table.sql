ALTER TABLE users
    ADD CONSTRAINT uk_email_users UNIQUE (email);