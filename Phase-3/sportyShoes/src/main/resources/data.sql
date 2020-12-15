USE SportyShoes;
delete from users;
insert into users (user_email, user_password, user_role)
values ('admin@sportyshoes.com', 'Pa$sw0rd', 'A');

delete from categories;
insert into categories(category_id, category_name, category_desc)
values (1, 'Lifestyle', 'Lifestyles of men & women');

delete from products;
insert into products(product_id, product_category, product_name, product_short_desc, product_long_desc, product_price)
values(1, 1, 'Nike Air Force 1 NDESTRUKT', 'Men''s Shoe,1 Colour', 'Put it through the ringer and still show up looking good. The Nike Air Force 1 NDESTRUKT re-imagines the b-ball icon with a tough, street-ready touch. Forged with TecTuff™ and durable rubber accents on the toe, heel and sides, it mixes action-sport style with everyday function.', 619);