DROP DATABASE IF EXISTS FoodBox;
CREATE DATABASE FoodBox;
USE FoodBox;
DROP USER IF EXISTS 'foodbox_user'@'localhost';
CREATE USER 'foodbox_user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON * . * TO 'foodbox_user'@'localhost';
FLUSH PRIVILEGES;