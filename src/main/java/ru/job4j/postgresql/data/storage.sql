create table body (
                      id serial primary key,
                      number int
);

create table engine(
                       id serial primary key,
                       power int,
                       fuel char
);

create table transmission(
                             id serial primary key,
                             number int
);

create table car(
                    id serial primary key,
                    name varchar,
                    body_id int references body(id),
                    engine_id int references engine(id),
                    transmission_id int references transmission(id)
);

insert into body(number) VALUES (11), (12),(13),(14),(15);
insert into engine(power, fuel) VALUES (100, 'G'), (130, 'D'), (120, 'G'), (123, 'D'), (140, 'D'), (132, 'D');
insert into transmission(number) VALUES (32), (42), (52), (62), (72), (82);

insert into car(name, body_id, engine_id, transmission_id) VALUES ('BMW', 1, 1, 1), ('MERSEDES', 2, 5, 2),('PORSHE', 4, 2, 3),('HAMMER', 2, 3, 1),
                                                                  ('Vaz', null, 2, 3), ('Uaz', 1, null, 4),
                                                                  ('Gaz', 2, 3, null);

select * from car;

/* вывести имя машины и привязанные к ним детали */
select c.name, b.number from car as c join body b on c.body_id = b.id;
select c.name, e.power from car as c join engine e on c.engine_id = e.id;
select c.name, t.number from car as c join transmission t on c.transmission_id = t.id;

/* вывести имя машины и номер детали которая не используется в никакой машине */
select c.name, b.number from car as c left join body b on c.body_id = b.id where body_id is null;
select c.name, t.number from car as c left join transmission t on c.transmission_id = t.id  where transmission_id is null;
select c.name, e.power from car as c left join engine e on c.engine_id = e.id  where engine_id is null;