drop schema if exists command_project cascade;

alter table if exists deliveries_tbl
    drop constraint if exists FK4u78all88mt04n485e1kj3ywp;

alter table if exists deliveries_tbl
    drop constraint if exists FKo0htns69b92ijvr9lcufqn6mb;


alter table if exists deliveries_tbl
    drop constraint if exists FKrhu3lnxh35ev90e3447moyl7g;

alter table if exists deliveries_tbl
    drop constraint if exists FKmaktt28lc90wy9g30hnbmdb5h;

alter table if exists deliveries_tbl
    drop constraint if exists FKcdt9ewm779lb5h5skkpa1rssu;


alter table if exists deliveries_tbl
    drop constraint if exists FK7rd9uput40ls1loeeh472vky3;

alter table if exists shops_tbl
    drop constraint if exists FKnoxb3i11d7g3tvyaihyevv5b5;

alter table if exists users_brands_tbl
    drop constraint if exists FKsp5jvkv8foa75voolvlsphxvd;

alter table if exists users_brands_tbl
    drop constraint if exists FKepkj6l3346hma22u1s2sh6xf1;

alter table if exists users_roles_tbl
    drop constraint if exists FKec55svjjqvr2r098g415jqrpp;


alter table if exists users_roles_tbl
    drop constraint if exists FK16ne3yb5jx0r56xxmb5bp6d82;

create table brands_tbl (
                                            id int8 generated by default as identity,
                                            abbr varchar(255),
                                            name varchar(255),
                                            primary key (id)
);


create table brands_tbl (
                                            id int8 generated by default as identity,
                                            abbr varchar(255),
                                            name varchar(255),
                                            primary key (id)
);

create table users_tbl (
                                           id int8 generated by default as identity,
                                           email varchar(255),
                                           full_name varchar(255),
                                           password varchar(255),
                                           phone varchar(255),
                                           username varchar(255),
                                           primary key (id)
);

create table deliveries_tbl (
                                id int8 generated by default as identity,
                                car_info varchar(255),
                                comment varchar(255),
                                delivery_date date,
                                driver_info varchar(255),
                                invoice varchar(255),
                                number_of_places varchar(255),
                                order_number varchar(255),
                                sender varchar(255),
                                torg_number varchar(255),
                                brand_id int8,
                                delivery_time_id int8,
                                delivery_type_id int8,
                                shop_id int8,
                                user_id int8,
                                warehouse_id int8,
                                primary key (id)
);


create table deliveries_tbl (
                                id int8 generated by default as identity,
                                car_info varchar(255),
                                comment varchar(255),
                                delivery_date date,
                                driver_info varchar(255),
                                invoice varchar(255),
                                number_of_places varchar(255),
                                order_number varchar(255),
                                sender varchar(255),
                                torg_number varchar(255),
                                brand_id int8,
                                delivery_time_id int8,
                                delivery_type_id int8,
                                shop_id int8,
                                user_id int8,
                                warehouse_id int8,
                                primary key (id)
);

create table delivery_time_tbl (
                                   id int8 generated by default as identity,
                                   delivery_time varchar(255),
                                   primary key (id)
);


create table delivery_time_tbl (
                                   id int8 generated by default as identity,
                                   delivery_time varchar(255),
                                   primary key (id)
);


create table delivery_type_tbl (
                                   id int8 generated by default as identity,
                                   type varchar(255),
                                   primary key (id)
);

create table delivery_type_tbl (
                                   id int8 generated by default as identity,
                                   type varchar(255),
                                   primary key (id)
);

create table roles_tbl (
                           id int8 generated by default as identity,
                           role varchar(255),
                           primary key (id)
);


create table roles_tbl (
                           id int8 generated by default as identity,
                           role varchar(255),
                           primary key (id)
);

create table shops_tbl (
                           id int8 generated by default as identity,
                           abbr varchar(255),
                           name varchar(255),
                           brand_id int8,
                           primary key (id)
);

create table shops_tbl (
                           id int8 generated by default as identity,
                           abbr varchar(255),
                           name varchar(255),
                           brand_id int8,
                           primary key (id)
);

create table users_brands_tbl (
                                  user_id int8 not null,
                                  brand_id int8 not null
);


create table users_brands_tbl (
                                  user_id int8 not null,
                                  brand_id int8 not null
);


create table users_roles_tbl (
                                 user_id int8 not null,
                                 role_id int8 not null
);

create table users_roles_tbl (
                                 user_id int8 not null,
                                 role_id int8 not null
);

create table warehouses_tbl (
                                id int8 generated by default as identity,
                                abbr varchar(255),
                                name varchar(255),
                                primary key (id)
);

create table warehouses_tbl (
                                id int8 generated by default as identity,
                                abbr varchar(255),
                                name varchar(255),
                                primary key (id)
);

alter table if exists deliveries_tbl
    add constraint FK4u78all88mt04n485e1kj3ywp
        foreign key (brand_id)
            references command_project.brands_tbl;

alter table if exists deliveries_tbl
    add constraint FKo0htns69b92ijvr9lcufqn6mb
        foreign key (delivery_time_id)
            references delivery_time_tbl;

alter table if exists deliveries_tbl
    add constraint FKrhu3lnxh35ev90e3447moyl7g
        foreign key (delivery_type_id)
            references delivery_type_tbl;

alter table if exists deliveries_tbl
    add constraint FKmaktt28lc90wy9g30hnbmdb5h
        foreign key (shop_id)
            references shops_tbl;

alter table if exists deliveries_tbl
    add constraint FKcdt9ewm779lb5h5skkpa1rssu
        foreign key (user_id)
            references command_project.users_tbl;

alter table if exists deliveries_tbl
    add constraint FK7rd9uput40ls1loeeh472vky3
        foreign key (warehouse_id)
            references warehouses_tbl;

alter table if exists shops_tbl
    add constraint FKnoxb3i11d7g3tvyaihyevv5b5
        foreign key (brand_id)
            references command_project.brands_tbl;

alter table if exists users_brands_tbl
    add constraint FKsp5jvkv8foa75voolvlsphxvd
        foreign key (brand_id)
            references command_project.brands_tbl;

alter table if exists users_brands_tbl
    add constraint FKepkj6l3346hma22u1s2sh6xf1
        foreign key (user_id)
            references command_project.users_tbl;

alter table if exists users_roles_tbl
    add constraint FKec55svjjqvr2r098g415jqrpp
        foreign key (role_id)
            references roles_tbl;

alter table if exists users_roles_tbl
    add constraint FK16ne3yb5jx0r56xxmb5bp6d82
        foreign key (user_id)
            references command_project.users_tbl;


INSERT INTO brands_tbl(
    name, abbr)
VALUES ('Goods House Авеню', 'GDH');

INSERT INTO brands_tbl(
    name, abbr)
VALUES ('Some brand', 'ckh');

INSERT INTO shops_tbl(
    name, abbr)
VALUES ('Goods House Авеню', 'GHAVENU');

INSERT INTO command_project.shops_tbl(
    name, abbr)
VALUES ('ООО Какой-то магазин', 'anyabbr');

INSERT INTO command_project.warehouses_tbl(
    name, abbr)
VALUES ('Склад №1', 'skd1');

INSERT INTO command_project.users_tbl (
    email, full_name, password, phone, username) VALUES (
                                                            'gogi@gmail.com'::character varying, 'Gogi Gogi'::character varying, '$2y$12$xirQ1vo.wozECP4rXSMzFuKLU6oGHVOBk0C/KcTxosLRR9Lug9k4i'::character varying, '88002222222'::character varying, 'Gogi'::character varying)
returning id;

INSERT INTO command_project.brands_tbl(
    name, abbr)
VALUES ('Goods House Авеню', 'GDH');

INSERT INTO command_project.brands_tbl(
    name, abbr)
VALUES ('Some brand', 'ckh');


INSERT INTO roles_tbl (
    role) VALUES (
                     'ROLE_ADMIN'::character varying)
returning id;

INSERT INTO roles_tbl (
    role) VALUES (
                     'ROLE_WAREHOUSE'::character varying)
returning id;

INSERT INTO roles_tbl (
    role) VALUES (
                     'ROLE_BRAND_MANAGER'::character varying)
returning id;


INSERT INTO shops_tbl (
    abbr, name, brand_id) VALUES (
                                     'GDH1'::character varying, 'Goods House Авеню'::character varying, '1'::bigint)
returning id;

INSERT INTO users_brands_tbl(
    user_id, brand_id)
VALUES (1, 1);

INSERT INTO delivery_type_tbl(
    id, type)
VALUES (1, 'CROSS_DOCKING');

INSERT INTO delivery_time_tbl(
    id, delivery_time)
VALUES (1, 'MORNING');


INSERT INTO deliveries_tbl (
    car_info, comment, delivery_date, driver_info, invoice, number_of_places, order_number, sender, torg_number, brand_id, delivery_time_id, delivery_type_id, shop_id, user_id, warehouse_id) VALUES (
       'Н496ХВ197'::character varying, 'доставить вовремя'::character varying, '10.03.2021'::date, 'Vasya'::character varying, '1123'::character varying, '40'::character varying, 'w12344'::character varying, 'ООО Какая-то компания'::character varying, '1123'::character varying, '1'::bigint, '1'::bigint, '1'::bigint, '1'::bigint, '1'::bigint, '1'::bigint)
returning id;
