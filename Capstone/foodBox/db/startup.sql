DROP DATABASE IF EXISTS foodbox;
CREATE DATABASE foodbox;
USE foodbox;
DROP USER IF EXISTS 'foodbox_user'@'localhost';
CREATE USER 'foodbox_user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON * . * TO 'foodbox_user'@'localhost';
FLUSH PRIVILEGES;
DROP TABLE IF EXISTS categories;
CREATE TABLE categories (
    category_id BIGINT NOT NULL AUTO_INCREMENT,
    category_name VARCHAR(255) NOT NULL,
    category_enabled BOOLEAN DEFAULT TRUE,
    category_image BLOB,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (category_id)
) ENGINE=INNODB;
DROP TABLE IF EXISTS users;
CREATE TABLE users (
    user_email VARCHAR(255) NOT NULL,
    user_password VARCHAR(255) NOT NULL,
    user_name VARCHAR(255) NOT NULL,
    user_phone VARCHAR(255) NOT NULL,
    user_role CHAR(1) NOT NULL,
    address_line1 VARCHAR(255),
    address_line2 VARCHAR(255),
    address_city VARCHAR(255),
    address_postcode VARCHAR(255),
    user_enabled BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_email)
) ENGINE=INNODB;
DROP TABLE IF EXISTS offers;
CREATE TABLE offers (
    offer_id BIGINT NOT NULL AUTO_INCREMENT,
    offer_name VARCHAR(255) NOT NULL,
    offer_discount_type CHAR(3) NOT NULL,
    offer_discount DECIMAL(18, 5) NOT NULL,
    PRIMARY KEY (offer_id)
) ENGINE=INNODB;
DROP TABLE IF EXISTS foods;
CREATE TABLE foods (
    food_id BIGINT NOT NULL AUTO_INCREMENT,
    food_name VARCHAR(255) NOT NULL,
    food_category BIGINT NOT NULL,
    food_price DECIMAL(18, 5) NOT NULL,
    food_desc VARCHAR(255) NOT NULL,
    food_offer BIGINT,
    food_image BLOB,
    food_enabled BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (food_id),
    CONSTRAINT fk_food_category FOREIGN KEY (food_category) REFERENCES categories(category_id),
    CONSTRAINT fk_food_offer FOREIGN KEY (food_offer) REFERENCES offers(offer_id)
) ENGINE=INNODB;
DROP TABLE IF EXISTS fees;
CREATE TABLE fees (
    fee_type VARCHAR(50) NOT NULL,
    fee_value DECIMAL(18, 5) NOT NULL DEFAULT 0,
    PRIMARY KEY (fee_type)
) ENGINE=INNODB;
DROP TABLE IF EXISTS orderitems;
CREATE TABLE orderitems (
    oitem_id BIGINT NOT NULL AUTO_INCREMENT,
    oitem_food BIGINT NOT NULL,
    oitem_quantity INT NOT NULL,
    oitem_price DECIMAL(18, 5) NOT NULL,
    oitem_discount DECIMAL(18, 5),
    PRIMARY KEY (oitem_id),
    CONSTRAINT fk_orderitems_food FOREIGN KEY (oitem_food) REFERENCES foods(food_id)
) ENGINE=INNODB;
DROP TABLE IF EXISTS orders;
CREATE TABLE orders (
    order_id BIGINT NOT NULL AUTO_INCREMENT,
    order_charge_id VARCHAR(255) NOT NULL,
    order_user VARCHAR(255) NOT NULL,
    order_price DECIMAL(18, 5) NOT NULL,
    order_discount DECIMAL(18, 5),
    order_delivery_fee DECIMAL(18, 5),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (order_id),
    CONSTRAINT fk_orders_user FOREIGN KEY (order_user) REFERENCES users(user_email)
) ENGINE=INNODB;
DROP TABLE IF EXISTS orderdetails;
CREATE TABLE orderdetails (
    order_id BIGINT NOT NULL,
    oitem_id BIGINT NOT NULL,
    CONSTRAINT fk_orders FOREIGN KEY (order_id) REFERENCES orders(order_id),
    CONSTRAINT fk_orderitems FOREIGN KEY (oitem_id) REFERENCES orderitems(oitem_id)
) ENGINE=INNODB;

DROP USER IF EXISTS 'foodbox_user'@'172.17.0.1';
CREATE USER 'foodbox_user'@'172.17.0.1' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON *.* TO 'foodbox_user'@'172.17.0.1' WITH GRANT OPTION;

DROP USER IF EXISTS 'foodbox_user'@'%';
CREATE USER 'foodbox_user'@'%' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON *.* TO 'foodbox_user'@'%' WITH GRANT OPTION;

delete from categories;
insert into categories(category_id, category_name) values (1, 'Chinese');
insert into categories(category_id, category_name) values (2, 'Indian');
insert into categories(category_id, category_name) values (3, 'Thai');
insert into categories(category_id, category_name) values (4, 'Korean');

delete from users;
insert into users (user_email, user_password, user_name, user_phone, user_role)
values ('admin@foodbox.com', 'Pa$sw0rd', 'admin', '+60-333411289', 'A');
insert into users (user_email, user_password, user_name, user_phone, user_role, address_line1, address_line2, address_city, address_postcode)
values ('johndoe@gmail.com', 'Pa$sw0rd', 'johndoe', '+60-127813456', 'U', '123 Sunshine Apartment', 'St NW', 'Kuala Lumpur', '57000');

delete from offers;
insert into offers (offer_id, offer_name, offer_discount_type, offer_discount)
values (1, '3.3 Only Deals', 'PCT', 5.0);
insert into offers (offer_id, offer_name, offer_discount_type, offer_discount)
values (2, 'Valentine''s Day Special', 'CSH', 3.0);

delete from foods;
insert into foods (food_id, food_name, food_category, food_price, food_desc, food_offer)
values (1, 'Lion'' Head Soup', 1, 6.5, 'Chinese soup best served with white sticky rice.', null);
insert into foods (food_id, food_name, food_category, food_price, food_desc, food_offer)
values (2, 'Spareribs', 1, 12.5, 'Tender, juicy, tangy ribs cooked on the grill.', 2);
insert into foods (food_id, food_name, food_category, food_price, food_desc, food_offer)
values (3, 'Sweet and Sour Chicken', 1, 15.5, 'Pan fried chicken cubes served with a sweet and sour sauce.', 1);

delete from fees;
insert into fees(fee_type, fee_value) VALUES('delivery', 5.0);
