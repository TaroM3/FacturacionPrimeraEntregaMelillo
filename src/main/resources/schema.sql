CREATE DATABASE coderhouse;

CREATE TABLE coderhouse.clients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    surname VARCHAR(50),
    email VARCHAR(50)
);

CREATE TABLE coderhouse.products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50),
    description VARCHAR(100),
    code VARCHAR(50),
    stock INT,
    price DOUBLE
);

CREATE TABLE coderhouse.sales (
    id INT AUTO_INCREMENT PRIMARY KEY,
    client_id INT,
    created_at DATETIME,
    total DOUBLE
);