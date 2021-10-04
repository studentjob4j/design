create table type(
                     id serial primary key,
                     name varchar(20)
);
create table product (
                         id serial primary key,
                         name varchar(20),
                         expired_date date,
                         price int,
                         type_id int references type(id)
);
insert into type(name) values ('Сыр');
insert into type(name) values ('Молоко');
insert into type(name) values ('Творог');
insert into type(name) values ('Мороженное');
select * from type;

insert into product(name, expired_date, price, type_id) values ('Французский', '2021-04-10', 250, 1);
insert into product(name, expired_date, price, type_id) values ('Фермерский', '2021-11-04', 2500, 1);
insert into product(name, expired_date, price, type_id) values ('Плавленный', '2021-06-13', 200, 1);

insert into product(name, expired_date, price, type_id) values ('Домашнее', '2021-04-03', 50, 2);
insert into product(name, expired_date, price, type_id) values ('Пастеризованное', '2021-10-13', 100, 2);
insert into product(name, expired_date, price, type_id) values ('Веселый Молочник', '2021-03-23', 75, 2);
insert into product(name, expired_date, price, type_id) values ('Любительское', '2021-11-04', 60, 2);

insert into product(name, expired_date, price, type_id) values ('Домашний', '2021-11-04', 300, 3);
insert into product(name, expired_date, price, type_id) values ('Бабушкин', '2021-03-17', 320, 3);

insert into product(name, expired_date, price, type_id) values ('Пломбир мороженное', '2021-05-13', 50, 4);
insert into product(name, expired_date, price, type_id) values ('Сникерс мороженное', '2021-04-13', 55, 4);
insert into product(name, expired_date, price, type_id) values ('Просто мороженное', '2021-11-04', 60, 4);
select * from product;

/*
Извлечь продукты у которых тип равен сыр;
*/
select p.name, t.name from product as p inner join type t on p.type_id = t.id where t.name = 'Сыр';

/* Извлечь продукты в имени которого встречается слово мороженное */
select * from product where product.name LIKE '%мороженное%';

/* Извлечь продукты у которых срок годности заканчивается ровно от сейчас + 1 месяц */
select product.name from product where product.expired_date = CURRENT_DATE + interval '1 month';

/* Извлечь продукт с макс ценой */
select max(price) from product;

/* выводит тип продукта и их количество */
select t.name, count(p.name) from type as t join product p on t.id = p.type_id group by t.name;

/* выводит продукты по имени с типом сыр или молоко */
select p.name , t.name from product as p inner join type t on p.type_id = t.id where t.name = 'Сыр' OR t.name = 'Молоко';

/* выводит тип продуктов которых осталось меньше 7 штук */
select t.name, count(p.name) from product as p join type t on p.type_id = t.id group by t.name having count(p.name) < 7;

/* вывести все продукты и их тип */
select p.name,p.price, p.expired_date, t.name from product as p full join type t on p.type_id = t.id;