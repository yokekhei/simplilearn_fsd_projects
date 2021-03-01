USE FoodBox;

delete from categories;
insert into categories(category_id, category_name) values (1, 'Chinese');

delete from users;
insert into users (user_email, user_password, user_name, user_role)
values ('admin@foodbox.com', 'Pa$sw0rd', 'admin', 'A');
insert into users (user_email, user_password, user_name, user_role, address_line1, address_line2, address_city, address_postcode)
values ('johndoe@gmail.com', 'Pa$sw0rd', 'johndoe', 'U', '123 Sunshine Apartment', 'St NW', 'Kuala Lumpur', '57000');

delete from offers;
insert into offers (offer_id, offer_name, offer_discount_type, offer_discount)
values (1, '3.3 Only Deals', 'PCT', 5.0);
insert into offers (offer_id, offer_name, offer_discount_type, offer_discount)
values (2, 'Valentine''s Day Special', 'CSH', 3.0);

delete from foods;
insert into foods (food_id, food_name, food_category, food_price, food_desc, food_offer)
values (1, 'Condensed Milk Hainan Toast', 1, 6.5, 'Breakfast/Supper cuisines', null);
insert into foods (food_id, food_name, food_category, food_price, food_desc, food_offer)
values (2, 'Prawn Meehoon & Mee', 1, 12.5, 'Lunch/Dinner cuisines', 2);
insert into foods (food_id, food_name, food_category, food_price, food_desc, food_offer)
values (3, 'Nasi Lemak with Chicken Rendang', 1, 15.5, 'Lunch/Dinner cuisines', 1);

delete from fees;
insert into fees(fee_type, fee_value) VALUES('delivery', 5.0);