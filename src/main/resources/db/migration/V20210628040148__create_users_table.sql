CREATE TABLE users
(
    id                 BIGINT UNSIGNED AUTO_INCREMENT,
    name               VARCHAR(255),
    email              VARCHAR(255),
    username           VARCHAR(255),
    phone              VARCHAR(255),
    password           VARCHAR(255),
    photo              VARCHAR(255),
    verified_date      TIMESTAMP,
    created_date       TIMESTAMP,
    created_by         BIGINT UNSIGNED,
    last_modified_date TIMESTAMP,
    last_modified_by   BIGINT UNSIGNED,
    deleted            BOOLEAN,
    PRIMARY KEY (id)
);