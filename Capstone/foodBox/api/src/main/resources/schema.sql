DROP DATABASE IF EXISTS FoodBox;
CREATE DATABASE FoodBox;
USE FoodBox;
DROP USER IF EXISTS 'foodbox_user'@'localhost';
CREATE USER 'foodbox_user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON * . * TO 'foodbox_user'@'localhost';
FLUSH PRIVILEGES;
DROP TABLE IF EXISTS Categories;
CREATE TABLE Categories (
    category_id BIGINT NOT NULL AUTO_INCREMENT,
    category_name VARCHAR(255) NOT NULL,
    category_enabled BOOLEAN DEFAULT TRUE,
    category_image BLOB,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (category_id)
) ENGINE=INNODB;
DROP TABLE IF EXISTS Users;
CREATE TABLE Users (
    user_email VARCHAR(255) NOT NULL,
    user_password VARCHAR(255) NOT NULL,
    user_name VARCHAR(255) NOT NULL,
    user_role CHAR(1) NOT NULL,
    address_line1 VARCHAR(255),
    address_line2 VARCHAR(255),
    address_city VARCHAR(255),
    address_postcode VARCHAR(255),
    user_enabled BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_email)
) ENGINE=INNODB;
DROP TABLE IF EXISTS Offers;
CREATE TABLE Offers (
	offer_id BIGINT NOT NULL AUTO_INCREMENT,
    offer_name VARCHAR(255) NOT NULL,
    offer_discount_type CHAR(3) NOT NULL,
    offer_discount DECIMAL(18, 5) NOT NULL,
    PRIMARY KEY (offer_id)
) ENGINE=INNODB;
DROP TABLE IF EXISTS Foods;
CREATE TABLE Foods (
	food_id BIGINT NOT NULL AUTO_INCREMENT,
    food_name VARCHAR(255) NOT NULL,
    food_category BIGINT NOT NULL,
    food_price DECIMAL(18, 5) NOT NULL,
    food_desc VARCHAR(255) NOT NULL,
    food_offer BIGINT,
    food_image BLOB,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (food_id),
    CONSTRAINT fk_food_category FOREIGN KEY (food_category) REFERENCES Categories(category_id),
    CONSTRAINT fk_food_offer FOREIGN KEY (food_offer) REFERENCES Offers(offer_id)
) ENGINE=INNODB;
DROP TABLE IF EXISTS Fees;
CREATE TABLE Fees (
	fee_type VARCHAR(50) NOT NULL,
    fee_value DECIMAL(18, 5) NOT NULL DEFAULT 0
) ENGINE=INNODB;