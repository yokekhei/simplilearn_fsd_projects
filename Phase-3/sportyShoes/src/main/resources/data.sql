USE SportyShoes;
delete from users;
insert into users (user_email, user_password, user_role)
values ('admin@sportyshoes.com', 'Pa$sw0rd', 'A');

delete from categories;
insert into categories(category_name, category_desc)
values ('Lifestyle', 'Lifestyles of men & women');