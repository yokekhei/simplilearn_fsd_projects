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
DROP TABLE IF EXISTS Place;
CREATE TABLE PLACE (
    location_code VARCHAR(3) NOT NULL,
    location_name VARCHAR(100) NOT NULL,
    city_name VARCHAR(100) NOT NULL,
    PRIMARY KEY (location_code)
) ENGINE=INNODB;
DROP TABLE IF EXISTS Airline;
CREATE TABLE Airline (
    airline_code INT NOT NULL,
    flight_code VARCHAR(2) NOT NULL,
    company_name VARCHAR(100) NOT NULL,
    country VARCHAR(100) NOT NULL,
    PRIMARY KEY (airline_code)
) ENGINE=INNODB;
DROP TABLE IF EXISTS Flight;
CREATE TABLE Flight (
    flight_id INT NOT NULL AUTO_INCREMENT,
    flight_no INT NOT NULL,
    airline_code INT NOT NULL,
    src_location VARCHAR(3) NOT NULL,
    dest_location VARCHAR(3) NOT NULL,
    depart_date DATE NOT NULL,
    depart_time TIME NOT NULL,
    arrive_date DATE NOT NULL,
    arrive_time TIME NOT NULL,
    adult_price DECIMAL(18, 5) NOT NULL,
    child_price DECIMAL(18, 5) NOT NULL,
    infant_price DECIMAL(18, 5) NOT NULL,
    PRIMARY KEY (flight_id),
    CONSTRAINT fk_airline FOREIGN KEY (airline_code) REFERENCES Airline(airline_code),
    CONSTRAINT fk_src_location FOREIGN KEY (src_location) REFERENCES Place(location_code),
    CONSTRAINT fk_dest_location FOREIGN KEY (dest_location) REFERENCES Place(location_code)
) ENGINE=INNODB;

/* DATA */
INSERT INTO AdminUser(admin_email, admin_password) VALUES('admin@flyaway.com', 'password');
INSERT INTO Place(location_code, location_name, city_name) VALUES('KUL', 'Metropolitan Area', 'Kuala Lumpur');
INSERT INTO Airline(airline_code, flight_code, company_name, country) VALUES(232, 'MH', 'Malaysia Airlines Berhad dba Malaysia Airlines', 'Malaysia');