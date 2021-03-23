-- drop schema if exists command_project cascade;
-- create schema command_project;
set search_path to command_project;

alter table if exists command_project.deliveries_tbl
    drop constraint if exists FK4u78all88mt04n485e1kj3ywp;

alter table if exists command_project.deliveries_tbl
    drop constraint if exists FKo0htns69b92ijvr9lcufqn6mb;

alter table if exists command_project.deliveries_tbl
    drop constraint if exists FKrhu3lnxh35ev90e3447moyl7g;


alter table if exists command_project.deliveries_tbl
    drop constraint if exists FKmaktt28lc90wy9g30hnbmdb5h;


alter table if exists command_project.deliveries_tbl
    drop constraint if exists FKcdt9ewm779lb5h5skkpa1rssu;


alter table if exists command_project.deliveries_tbl
    drop constraint if exists FK7rd9uput40ls1loeeh472vky3;

alter table if exists command_project.shops_tbl
    drop constraint if exists FKnoxb3i11d7g3tvyaihyevv5b5;


alter table if exists command_project.users_brands_tbl
    drop constraint if exists FKsp5jvkv8foa75voolvlsphxvd;

alter table if exists command_project.users_brands_tbl
    drop constraint if exists FKepkj6l3346hma22u1s2sh6xf1;

alter table if exists command_project.users_roles_tbl
    drop constraint if exists FKec55svjjqvr2r098g415jqrpp;

alter table if exists command_project.users_roles_tbl
    drop constraint if exists FK16ne3yb5jx0r56xxmb5bp6d82;

create table command_project.brands_tbl (
                                            id int8 generated by default as identity,
                                            abbr varchar(255),
                                            name varchar(255),
                                            primary key (id)
);


create table command_project.deliveries_tbl (
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


create table command_project.delivery_time_tbl (
                                                   id int8 generated by default as identity,
                                                   delivery_time varchar(255),
                                                   primary key (id)
);


create table command_project.delivery_type_tbl (
                                                   id int8 generated by default as identity,
                                                   type varchar(255),
                                                   primary key (id)
);


create table command_project.roles_tbl (
                                           id int8 generated by default as identity,
                                           role varchar(255),
                                           primary key (id)
);


create table command_project.shops_tbl (
                                           id int8 generated by default as identity,
                                           abbr varchar(255),
                                           name varchar(255),
                                           brand_id int8,
                                           primary key (id)
);


create table command_project.users_brands_tbl (
                                                  user_id int8 not null,
                                                  brand_id int8 not null
);


create table command_project.users_tbl (
                                           id int8 generated by default as identity,
                                           email varchar(255),
                                           full_name varchar(255),
                                           password varchar(255),
                                           phone varchar(255),
                                           username varchar(255),
                                           primary key (id)
);

create table command_project.users_roles_tbl (
                                                 user_id int8 not null,
                                                 role_id int8 not null
);



create table command_project.warehouses_tbl (
                                                id int8 generated by default as identity,
                                                abbr varchar(255),
                                                name varchar(255),
                                                primary key (id)
);

alter table if exists command_project.deliveries_tbl
    add constraint FK4u78all88mt04n485e1kj3ywp
        foreign key (brand_id)
            references command_project.brands_tbl;


alter table if exists command_project.deliveries_tbl
    add constraint FKo0htns69b92ijvr9lcufqn6mb
        foreign key (delivery_time_id)
            references command_project.delivery_time_tbl;

alter table if exists command_project.deliveries_tbl
    add constraint FKrhu3lnxh35ev90e3447moyl7g
        foreign key (delivery_type_id)
            references command_project.delivery_type_tbl;

alter table if exists command_project.deliveries_tbl
    add constraint FKmaktt28lc90wy9g30hnbmdb5h
        foreign key (shop_id)
            references command_project.shops_tbl;

alter table if exists command_project.deliveries_tbl
    add constraint FKcdt9ewm779lb5h5skkpa1rssu
        foreign key (user_id)
            references command_project.users_tbl;


alter table if exists command_project.deliveries_tbl
    add constraint FK7rd9uput40ls1loeeh472vky3
        foreign key (warehouse_id)
            references command_project.warehouses_tbl;

alter table if exists command_project.shops_tbl
    add constraint FKnoxb3i11d7g3tvyaihyevv5b5
        foreign key (brand_id)
            references command_project.brands_tbl;

alter table if exists command_project.users_brands_tbl
    add constraint FKsp5jvkv8foa75voolvlsphxvd
        foreign key (brand_id)
            references command_project.brands_tbl;

alter table if exists command_project.users_brands_tbl
    add constraint FKepkj6l3346hma22u1s2sh6xf1
        foreign key (user_id)
            references command_project.users_tbl;

alter table if exists command_project.users_roles_tbl
    add constraint FKec55svjjqvr2r098g415jqrpp
        foreign key (role_id)
            references command_project.roles_tbl;

alter table if exists command_project.users_roles_tbl
    add constraint FK16ne3yb5jx0r56xxmb5bp6d82
        foreign key (user_id)
            references command_project.users_tbl;

INSERT INTO command_project.brands_tbl(
    abbr, name)
VALUES ('GDH','Goods House');

INSERT INTO command_project.brands_tbl(
    abbr, name)
VALUES ('BH2','Bad House');


INSERT INTO shops_tbl (
    abbr, name, brand_id) VALUES (
                                     'GDH1', 'Goods House Авеню', '1');

INSERT INTO shops_tbl (
    abbr, name, brand_id) VALUES (
                                     'BH1', 'Bad House Авеню', '2');

INSERT INTO warehouses_tbl(
    name, abbr)
VALUES ('Склад №1', 'skd1');

INSERT INTO users_tbl (
    email, full_name, password, phone, username) VALUES (
                                                            'gogi@gmail.com', 'Gogi Gogi', '$2y$12$xirQ1vo.wozECP4rXSMzFuKLU6oGHVOBk0C/KcTxosLRR9Lug9k4i', '88002222222', 'Gogi'
                                                        );

INSERT INTO users_tbl (
    email, full_name, password, phone, username) VALUES (
                                                            'vasya@gmail.com', 'Vasya Lozhkin', 'password', '88003333333', 'Vasya'
                                                        );


INSERT INTO roles_tbl (
    role) VALUES (
                     'ROLE_ADMIN');

INSERT INTO roles_tbl (
    role) VALUES (
                     'ROLE_WAREHOUSE');


INSERT INTO roles_tbl (
    role) VALUES (
                     'ROLE_BRAND_MANAGER');


INSERT INTO users_roles_tbl (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles_tbl (user_id, role_id) VALUES (2, 2);

INSERT INTO users_brands_tbl(
    user_id, brand_id)
VALUES (1, 1);

INSERT INTO users_brands_tbl(
    user_id, brand_id)
VALUES (2, 2);

INSERT INTO delivery_type_tbl(
    id, type)
VALUES (1, 'CROSS_DOCKING');

INSERT INTO delivery_type_tbl(
    id,type)
VALUES (2,'TO_WAREHOUSE');

INSERT INTO delivery_time_tbl(
    id, delivery_time)
VALUES (1, 'Первая половина дня');

INSERT INTO delivery_time_tbl(
    id, delivery_time)
VALUES (2,'Вторая половина дня');

INSERT INTO deliveries_tbl (
    car_info, comment, delivery_date, driver_info, invoice, number_of_places,
    order_number, sender, torg_number, brand_id, delivery_time_id,
    delivery_type_id, shop_id, user_id, warehouse_id) VALUES (
                                                                 'Н496ХВ197', 'доставить вовремя', '10.03.2021',
                                                                 'Gogi', '1123', '40', 'w12344',
                                                                 'ООО Какая-то компания', '1123', 1, 1, 1, 1, 1, 1);

INSERT INTO deliveries_tbl (
    car_info, comment, delivery_date, driver_info, invoice, number_of_places,
    order_number, sender, torg_number, brand_id, delivery_time_id,
    delivery_type_id, shop_id, user_id, warehouse_id) VALUES (
                                                                 'Е855ВА177', 'доставить без опазаданий', '30.04.2021',
                                                                 'Petro', '1126', '20', 'w123444',
                                                                 'ООО Редиска', 'w123444', 2, 2, 2, 2, 2, 1);
