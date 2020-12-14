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