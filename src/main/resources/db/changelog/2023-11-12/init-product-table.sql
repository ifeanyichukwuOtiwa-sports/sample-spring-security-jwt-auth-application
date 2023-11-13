-- liquibase formatted sql
-- changeset ifeanyichukwuOtiwa-Sports:init_schema_product


CREATE TABLE IF NOT EXISTS product (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10,2),
    INDEX (name)
);