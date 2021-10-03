create table profession (
                            id serial primary key,
                            description text,
                            salary int
);

create table person (
                        id serial primary key,
                        name varchar(20),
                        age int,
                        address varchar(30),
                        profession_id int references profession(id)
);

insert into profession(description, salary) VALUES ('Проджект менеджер', 200);
insert into profession(description, salary) VALUES ('Девопс', 150);
insert into profession(description, salary) VALUES ('Программист', 100);
insert into profession(description, salary) VALUES ('Маркетолог', 130);
select * from profession;
insert into person(name, age, address, profession_id) VALUES ('Василий', 32, 'Владивосток', 2);
insert into person(name, age, address, profession_id) VALUES ('Николай', 22, 'Владивосток', 1);
insert into person(name, age, address, profession_id) VALUES ('Евгений', 35, 'Владивосток', 3);
insert into person(name, age, address, profession_id) VALUES ('Юлия', 32, 'Владивосток', 4);
insert into person(name, age, address, profession_id) VALUES ('Петр',null,'Владивосток', null);
select * from person;

select p.name ,p.age, pr.description from person as p join profession as pr on  p.id = pr.id;
select p.name ,p.age, p.address, pr.description from person as p  join profession as pr on  p.id = pr.id;
select p.name , pr.description from person as p  join profession as pr on  p.id = pr.id;
select p.address ,p.age, pr.description from person as p join profession as pr on  p.id = pr.id;