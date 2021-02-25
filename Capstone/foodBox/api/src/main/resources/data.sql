USE FoodBox;

delete from categories;
insert into categories(category_id, category_name) values (1, 'Rice');
insert into categories(category_id, category_name) values (2, 'Noodles');

delete from users;
insert into users (user_email, user_password, user_name, user_role)
values ('admin@foodbox.com', 'Pa$sw0rd', 'admin', 'A');
insert into users (user_email, user_password, user_name, user_role, address_line1, address_line2, address_city, address_postcode)
values ('johndoe@gmail.com', 'Pa$sw0rd', 'johndoe', 'U', '123 Sunshine Apartment', 'St NW', 'Kuala Lumpur', '57000');