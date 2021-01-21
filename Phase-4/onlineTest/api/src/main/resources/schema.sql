DROP DATABASE IF EXISTS OnlineTest;
CREATE DATABASE OnlineTest;
USE OnlineTest;
DROP USER IF EXISTS 'onlinetest_user'@'localhost';
CREATE USER 'onlinetest_user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON * . * TO 'onlinetest_user'@'localhost';
FLUSH PRIVILEGES;
DROP TABLE IF EXISTS Categories;
CREATE TABLE Categories (
    category_id BIGINT NOT NULL AUTO_INCREMENT,
    category_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (category_id)
) ENGINE=INNODB;