USE OnlineTest;

delete from categories;
insert into categories(category_name) values ('Mathematics');
insert into categories(category_name) values ('English and Language Arts');

delete from users;
insert into users (user_email, user_password, user_name, user_role)
values ('admin@onlintest.com', 'Pa$sw0rd', 'admin', 'A');
insert into users (user_email, user_password, user_name, user_role)
values ('johndoe@gmail.com', 'Pa$sw0rd', 'johndoe', 'R');
insert into users (user_email, user_password, user_name, user_role)
values ('marry@gmail.com', 'Pa$sw0rd', 'marry', 'E');