DROP DATABASE IF EXISTS OnlineTest;
CREATE DATABASE OnlineTest;
USE OnlineTest;
DROP USER IF EXISTS 'onlinetest_user'@'localhost';
CREATE USER 'onlinetest_user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON * . * TO 'onlinetest_user'@'localhost';
FLUSH PRIVILEGES;