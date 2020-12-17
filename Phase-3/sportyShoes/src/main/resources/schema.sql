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
DROP TABLE IF EXISTS PurchaseItems;
CREATE TABLE PurchaseItems (
    pitem_id BIGINT NOT NULL AUTO_INCREMENT,
    pitem_product BIGINT NOT NULL,
    pitem_quantity INT NOT NULL,
    pitem_price DECIMAL(18, 5) NOT NULL,
    PRIMARY KEY (pitem_id),
    CONSTRAINT fk_purchaseitems_product FOREIGN KEY (pitem_product) REFERENCES Products(product_id)
) ENGINE=INNODB;
DROP TABLE IF EXISTS Purchases;
CREATE TABLE Purchases (
    purchase_id BIGINT NOT NULL AUTO_INCREMENT,
    purchase_user VARCHAR(255) NOT NULL,
    purchase_price DECIMAL(18, 5) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (purchase_id),
    CONSTRAINT fk_purchases_user FOREIGN KEY (purchase_user) REFERENCES Users(user_email)
) ENGINE=INNODB;
DROP TABLE IF EXISTS PurchaseDetails;
CREATE TABLE PurchaseDetails (
    purchase_id BIGINT NOT NULL,
    pitem_id BIGINT NOT NULL,
    CONSTRAINT fk_purchases FOREIGN KEY (purchase_id) REFERENCES Purchases(purchase_id),
    CONSTRAINT fk_purchaseitems FOREIGN KEY (pitem_id) REFERENCES PurchaseItems(pitem_id)
) ENGINE=INNODB;
DROP TABLE IF EXISTS Payment;
CREATE TABLE Payment (
	payment_id BIGINT NOT NULL AUTO_INCREMENT,
    purchase_id BIGINT NOT NULL,
    payor_name VARCHAR(100) NOT NULL,
    total_paid DECIMAL(18, 5) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (payment_id),
    CONSTRAINT fk_payment_purchase FOREIGN KEY (purchase_id) REFERENCES Purchases(purchase_id)
) ENGINE=INNODB;