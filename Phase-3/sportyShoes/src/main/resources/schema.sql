DROP DATABASE IF EXISTS SportyShoes;
CREATE DATABASE SportyShoes;
USE SportyShoes;
DROP USER IF EXISTS 'sportyshoes_user'@'localhost';
CREATE USER 'sportyshoes_user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON * . * TO 'sportyshoes_user'@'localhost';
FLUSH PRIVILEGES;
DROP TABLE IF EXISTS Users;
CREATE TABLE Users (
    user_email VARCHAR(255) NOT NULL,
    user_password VARCHAR(255) NOT NULL,
    user_fname VARCHAR(255),
    user_lname VARCHAR(255),
    user_dob DATE,
    user_gender CHAR(1),
    user_role CHAR(1) NOT NULL,
    user_enabled BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_email)
) ENGINE=INNODB;
DROP TABLE IF EXISTS Categories;
CREATE TABLE Categories (
    category_id BIGINT NOT NULL AUTO_INCREMENT,
    category_name VARCHAR(255) NOT NULL,
    category_desc VARCHAR(255) NOT NULL,
    PRIMARY KEY (category_id)
) ENGINE=INNODB;
DROP TABLE IF EXISTS Products;
CREATE TABLE Products (
    product_id BIGINT NOT NULL AUTO_INCREMENT,
    product_category BIGINT NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    product_short_desc VARCHAR(255) NOT NULL,
    product_long_desc VARCHAR(1024) NOT NULL,
    product_price DECIMAL(18, 5) NOT NULL,
    product_picture BLOB,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (product_id),
    CONSTRAINT fk_products_category FOREIGN KEY (product_category) REFERENCES Categories(category_id)
) ENGINE=INNODB;