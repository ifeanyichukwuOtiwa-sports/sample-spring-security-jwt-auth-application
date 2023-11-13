-- liquibase formatted sql
-- changeset ifeanyichukwuOtiwa-Sports:init-users-table


CREATE TABLE IF NOT EXISTS user(
    id BINARY(16) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    INDEX (username),
    INDEX (email)
)