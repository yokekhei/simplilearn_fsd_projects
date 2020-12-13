DROP DATABASE IF EXISTS SportyShoes;
CREATE DATABASE SportyShoes;
USE SportyShoes;
DROP USER IF EXISTS 'sportyshoes_user'@'localhost';
CREATE USER 'sportyshoes_user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON * . * TO 'sportyshoes_user'@'localhost';
FLUSH PRIVILEGES;