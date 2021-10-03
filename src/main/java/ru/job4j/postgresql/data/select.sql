create table fauna (
                       id serial primary key,
                       name text,
                       avg_age int,
                       discovery_date date
);

insert into fauna (name, avg_age, discovery_date) VALUES ('any-fish-animal', 10, '1900-01-23');
select * from fauna;
insert into fauna (name, avg_age, discovery_date) VALUES ('predator', 15000, '1600-12-11');
insert into fauna (name, avg_age, discovery_date) VALUES ('cow', 12, null);

select * from fauna where name LIKE '%fish%';
select * from fauna where avg_age > 10000 AND avg_age < 20000;
select * from fauna where discovery_date  is null ;
select  * from fauna where discovery_date < '1960-01-01';