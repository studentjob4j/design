create table departments(
                            id serial primary key,
                            name varchar(20),
                            phone int
);

create table employees(
                          id serial primary key,
                          name varchar(20),
                          departments_id int references departments(id)
);

insert into departments(name, phone) VALUES ('Продажи', 2233);
insert into departments(name, phone) VALUES ('Маркетинг', 1144);
insert into departments(name, phone) VALUES ('Производство', 5566);
insert into departments(name, phone) VALUES ('Доставка', 8899);

insert into employees(name, departments_id) VALUES ('Вася', 1);
insert into employees(name, departments_id) VALUES ('Коля', 2);
insert into employees(name, departments_id) VALUES ('Петя', 3);
insert into employees(name, departments_id) VALUES ('Антон', 3);
insert into employees(name, departments_id) VALUES ('', 1);
insert into employees(name, departments_id) VALUES ('Света', null);

select * from departments;
select * from employees;

select * from departments d left join employees e on d.id = e.departments_id;
select * from departments d right join employees e on d.id = e.departments_id;
select * from departments d full join employees e on d.id = e.departments_id;
select * from departments d cross join employees e ;
select * from departments d left join employees e on d.id = e.departments_id where e.name is null ;

create table teen(
                     id serial primary key,
                     name varchar(20),
                     gender char
);

insert into teen(name, gender) VALUES ('Саша', 'М'), ('Коля', 'М'), ('Юля', 'Ж'), ('Наташа', 'Ж');

/* Используя cross join составить все возможные разнополые пары */
select t1.name, t2.name from teen as t1 cross join teen t2 where t1.gender <> t2.gender;