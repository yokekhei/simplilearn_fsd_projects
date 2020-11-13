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
DROP TABLE IF EXISTS Guest;
CREATE TABLE Guest (
	guest_email VARCHAR(100) NOT NULL,
    guest_fname VARCHAR(50) NOT NULL,
    guest_lname VARCHAR(50) NOT NULL,
    guest_phone VARCHAR(50) NOT NULL,
    PRIMARY KEY (guest_email)
) ENGINE=INNODB;
DROP TABLE IF EXISTS Booking;
CREATE TABLE Booking (
    booking_id INT NOT NULL AUTO_INCREMENT,
    flight_id INT NOT NULL,
    guest_email VARCHAR(100) NOT NULL,
    total_adult_fare DECIMAL(18, 5) NOT NULL,
    total_child_fare DECIMAL(18, 5) NOT NULL DEFAULT 0,
    total_infant_fare DECIMAL(18, 5) NOT NULL DEFAULT 0,
    total_passenger_service_charge DECIMAL(18, 5) NOT NULL DEFAULT 0,
    total_service_tax DECIMAL(18, 5) NOT NULL DEFAULT 0,
    total_regulatory_service_charge DECIMAL(18, 5) NOT NULL DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (booking_id),
    CONSTRAINT fk_guest_email FOREIGN KEY (guest_email) REFERENCES Guest(guest_email)
) ENGINE=INNODB;
DROP TABLE IF EXISTS Passenger;
CREATE TABLE Passenger (
	passenger_id INT NOT NULL AUTO_INCREMENT,
    passenger_fname VARCHAR(50) NOT NULL,
    passenger_lname VARCHAR(50) NOT NULL,
    passenger_dob DATE NOT NULL,
    passenger_gender CHAR(1) NOT NULL,
    passenger_type CHAR(1) NOT NULL,
    PRIMARY KEY (passenger_id)
) ENGINE=INNODB;
DROP TABLE IF EXISTS Booking_Passenger;
CREATE TABLE Booking_Passenger (
	booking_id INT NOT NULL,
    passenger_id INT NOT NULL,
    CONSTRAINT fk_booking FOREIGN KEY (booking_id) REFERENCES Booking(booking_id),
    CONSTRAINT fk_passenger FOREIGN KEY (passenger_id) REFERENCES Passenger(passenger_id)
) ENGINE=INNODB;
DROP TABLE IF EXISTS Fee;
CREATE TABLE Fee (
	fee_type VARCHAR(50) NOT NULL,
    fee_value DECIMAL(18, 5) NOT NULL DEFAULT 0
) ENGINE=INNODB;
DROP TABLE IF EXISTS Payment;
CREATE TABLE Payment (
	payment_id INT NOT NULL AUTO_INCREMENT,
    booking_id INT NOT NULL,
    payor_name VARCHAR(100) NOT NULL,
    total_paid DECIMAL(18, 5) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (payment_id),
    CONSTRAINT fk_payment_booking FOREIGN KEY (booking_id) REFERENCES Booking(booking_id)
) ENGINE=INNODB;

/* DATA */
INSERT INTO AdminUser(admin_email, admin_password) VALUES('admin@flyaway.com', 'password');
INSERT INTO Place(location_code, location_name, city_name) VALUES('KUL', 'Metropolitan Area', 'Kuala Lumpur');
INSERT INTO Airline(airline_code, flight_code, company_name, country) VALUES(232, 'MH', 'Malaysia Airlines Berhad dba Malaysia Airlines', 'Malaysia');
INSERT INTO Fee(fee_type, fee_value) VALUES("passenger_service_change", 11.0), ("regulatory_service_charge", 1.0), ("service_tax", 3.53);