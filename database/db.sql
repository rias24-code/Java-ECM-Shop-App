-- Create the database
CREATE DATABASE IF NOT EXISTS project;
USE project;
SHOW TABLES;

CREATE TABLE IF NOT EXISTS userdetail (
    uid INT AUTO_INCREMENT PRIMARY KEY,
    uname VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    role ENUM('admin', 'customer') NOT NULL
);

INSERT INTO userdetail (uname, password, role) VALUES
('admin', 'admin123', 'admin'),
('john', 'john123', 'customer'),
('mary', 'mary123', 'customer');

CREATE TABLE IF NOT EXISTS items (
    pid INT AUTO_INCREMENT PRIMARY KEY,
    pname VARCHAR(100) NOT NULL,
    category VARCHAR(50),
    quentity INT,
    unit VARCHAR(10),
    price INT
);

INSERT INTO items (pname, category, quentity, unit, price) VALUES
('Rice', 'Food', 50, 'kg', 60),
('Oil', 'Grocery', 30, 'ltr', 140),
('Soap', 'Personal Care', 100, 'pcs', 30);

CREATE TABLE IF NOT EXISTS orderHistory (
    oid INT AUTO_INCREMENT PRIMARY KEY,
    cus_name VARCHAR(50),
    pname VARCHAR(100),
    quantity INT,
    unit VARCHAR(10),
    price INT
);

CREATE TABLE IF NOT EXISTS product (
    pid INT AUTO_INCREMENT PRIMARY KEY,
    pname VARCHAR(100) NOT NULL,
    category VARCHAR(50),
    quantity INT,
    unit VARCHAR(10),
    price INT
);

INSERT INTO product (pname, category, quantity, unit, price) VALUES
('Rice', 'Grocery', 50, 'kg', 60),
('Oil', 'Grocery', 30, 'ltr', 140),
('Soap', 'Personal Care', 100, 'pcs', 30),
('Shampoo', 'Personal Care', 75, 'ml', 120),
('Notebook', 'Stationery', 200, 'pcs', 40);

SELECT * FROM items;
SELECT * FROM orderhistory;
SELECT * FROM userdetail;
SELECT * FROM product;
