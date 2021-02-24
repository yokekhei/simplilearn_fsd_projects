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