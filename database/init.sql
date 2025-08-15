-- Create database
CREATE DATABASE customer_order_portal;

-- Connect to the database
\c customer_order_portal;

-- Create tables (these will be created by Hibernate, but here's the schema for reference)
/*
CREATE TABLE clients (
    id BIGSERIAL PRIMARY KEY,
    client_id VARCHAR(255) NOT NULL UNIQUE,
    store_name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL
);

CREATE TABLE upcoming_deliveries (
    id BIGSERIAL PRIMARY KEY,
    category_name VARCHAR(255) NOT NULL,
    additional_description VARCHAR(255),
    delivery_week INTEGER NOT NULL,
    client_id VARCHAR(255) NOT NULL
);

CREATE TABLE orders (
    id BIGSERIAL PRIMARY KEY,
    po_number VARCHAR(255) NOT NULL UNIQUE,
    order_date DATE NOT NULL,
    expected_delivery DATE NOT NULL,
    status VARCHAR(50) NOT NULL,
    client_id VARCHAR(255) NOT NULL
);

CREATE TABLE order_lines (
    id BIGSERIAL PRIMARY KEY,
    order_num BIGINT NOT NULL,
    line INTEGER NOT NULL,
    product_code VARCHAR(255) NOT NULL,
    ean_code VARCHAR(255) NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    qty DECIMAL(10,2) NOT NULL,
    delivery_date DATE NOT NULL,
    po_number VARCHAR(255) NOT NULL
);
*/
