/* SCHEMA */
DROP DATABASE IF EXISTS FlyAway;
CREATE DATABASE FlyAway;
USE FlyAway;
DROP USER IF EXISTS 'flyaway_user'@'localhost';
CREATE USER 'flyaway_user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON * . * TO 'flyaway_user'@'localhost';
FLUSH PRIVILEGES;
DROP TABLE IF EXISTS AdminUser;
CREATE TABLE AdminUser (
    admin_email VARCHAR(100) NOT NULL,
    admin_password VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (admin_email)
) ENGINE=INNODB;

/* DATA */
INSERT INTO AdminUser(admin_email, admin_password) VALUES('admin@flyaway.com', 'password');