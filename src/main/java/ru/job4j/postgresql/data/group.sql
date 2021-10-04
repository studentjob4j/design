create table students(
                         id serial primary key,
                         name text
);

create table subjects(
                         id serial primary key,
                         name text
);

create table students_subjects(
                                  id serial primary key,
                                  mark float,
                                  std_id int references students(id),
                                  sbj_id int references subjects(id)
);

insert into students(name) values ('Аня'), ('Ваня'), ('Боря');
insert into subjects(name) values ('Математика'), ('Русский'), ('Информатика');
insert into students_subjects(std_id, sbj_id, mark) values (1, 1, 5), (1, 2, 5), (1, 3, 5);
insert into students_subjects(std_id, sbj_id, mark) values (2, 1, 5), (2, 2, 4), (2, 3, 4);
insert into students_subjects(std_id, sbj_id, mark) values (3, 1, 3), (3, 2, 5), (3, 3, 3);

select * from students_subjects;

select avg(mark) from students_subjects;
select min(mark) from students_subjects;
select max(mark) from students_subjects;

select s.name, avg(ss.mark) from students_subjects as ss join subjects s on ss.sbj_id = s.id
group by s.name;

select s.name, avg(ss.mark) from students_subjects as ss join students s on ss.std_id = s.id
group by s.name;

select s.name, avg(ss.mark) from students_subjects as ss join subjects s on ss.sbj_id = s.id group by s.name
having avg(ss.mark) > 4.2;

create table devices(
                        id serial primary key,
                        name varchar(255),
                        price float
);

create table people(
                       id serial primary key,
                       name varchar(255)
);

create table devices_people(
                               id serial primary key,
                               device_id int references devices(id),
                               people_id int references people(id)
);

insert into people(name) values ('Vangok');
insert into people(name) values ('Chupachups');
insert into people(name) values ('Likee');
select * from people;

insert into devices(name, price) values ('samsung', 12);
insert into devices(name, price) values ('apple', 23);
insert into devices(name, price) values ('motorolla', 11);
insert into devices(name, price) values ('huawei', 45);
insert into devices(name, price) values ('sony', 10);
insert into devices(name, price) values ('dji', 53);
select * from devices;

insert into devices_people(device_id, people_id) values (1, 1);
insert into devices_people(device_id, people_id) values (1, 2);
insert into devices_people(device_id, people_id) values (3, 2);
insert into devices_people(device_id, people_id) values (4, 2);
insert into devices_people(device_id, people_id) values (3, 3);
insert into devices_people(device_id, people_id) values (5, 3);
insert into devices_people(device_id, people_id) values (6, 3);
select * from devices_people;

/* выводим среднюю цену устройств */
select avg(price) from devices;

/* вывести для каждого человека среднюю цену его устройств. */
select p.name, avg(d.price)from people as p join devices d on p.id = d.id group by p.name;

/* вывести для каждого человека среднюю цену его устройств которая больше 12 */
select p.name, avg(d.price) from people as p join devices d on p.id = d.id group by p.name having avg(d.price) > 12;