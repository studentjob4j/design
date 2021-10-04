create table status(
                       id serial primary key,
                       status boolean
);
create table rules(
                      id serial primary key,
                      description varchar(30)
);
create table category(
                         id serial primary key,
                         description text
);
/* внешний ключ всегда со стороны many */
create table anyUser(
                        id serial primary key,
                        name varchar(10),
                        birhday date,
                        role_id references role(id)
);

create table role(
                     id serial primary key,
                     profession varchar(30),

);

/* внешний ключ всегда со стороны many */
create table role_rules(
                           id serial primary key,
                           id_role int references role(id),
                           id_rules int references rules(id)
);

/* внешний ключ всегда со стороны many */
create table item(
                     id serial primary key ,
                     name varchar(30),
                     category_id int references category(id),
                     status_id int references status(id),
                     anyuser_id int references anyUser(id)
);

/* внешний ключ всегда со стороны many */
create table comments(
                         id serial primary key,
                         description text,
                         item_id int references item(id)
);

/* внешний ключ всегда со стороны many */
create table attachs(
                        id serial primary key,
                        dataCreate date,
                        attaches_id int references item(id)
);