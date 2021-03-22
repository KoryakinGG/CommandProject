-- DROP SCHEMA IF EXISTS command_project CASCADE;
-- create schema command_project;
set search_path to command_project;

CREATE TABLE command_project.users_tbl
(
    id bigserial primary key,
    username character varying(255),
    password character varying(255),
    full_name character varying(255),
    email character varying(255),
    phone character varying(255),
    role character varying(255),
    warehouse_id bigint
);

CREATE TABLE command_project.brands_tbl
(
    id bigserial primary key,
    name character varying(255),
    abbr character varying(255),
    shop_id bigint
);

CREATE TABLE command_project.deliveries_tbl
(
    id bigserial,
    delivery_date timestamp,
    delivery_time character varying(255),
    car_info character varying(255),
    driver_info character varying(255),
    order_number character varying(255),
    delivery_type character varying(255),
    sender character varying(255),
    comment character varying(255),
    number_of_places character varying(255),
    torg_number character varying(255),
    invoice character varying(255),
    user_id bigint,
    brand_id bigint,
    shop_id bigint,
    warehouse_id bigint
);

CREATE TABLE command_project.shops_tbl
(
    id bigserial,
    name character varying(255),
    abbr character varying(255),
    brand_id bigint
);

CREATE TABLE command_project.warehouses_tbl
(
    id bigserial,
    name character varying(255),
    abbr character varying(255)
);


CREATE TABLE command_project.users_brands_tbl
(
    user_id bigint REFERENCES users_tbl (id),
    brand_id bigint references brands_tbl (id)
);

create table command_project.brands_shops_tbl (
    brand_id bigint,
    shop_id bigint
);


insert into command_project.users_tbl (username, password, full_name, email, phone, role, warehouse_id)
values ('Alex', '$2y$12$xirQ1vo.wozECP4rXSMzFuKLU6oGHVOBk0C/KcTxosLRR9Lug9k4i', 'Alex Firsh', 'alex@ideas4transfer.ru', '88002222200', 'ADMIN', 1);

insert into command_project.users_tbl (username, password, full_name, email, phone, role, warehouse_id)
values ('John', '$2y$12$xirQ1vo.wozECP4rXSMzFuKLU6oGHVOBk0C/KcTxosLRR9Lug9k4i', 'John Smith', 'john@ideas4transfer.ru', '88002222222', 'BRAND_MANAGER', 1);

insert into command_project.users_tbl (username, password, full_name, email, phone, role, warehouse_id)
values ('Vasya', '$2y$12$xirQ1vo.wozECP4rXSMzFuKLU6oGHVOBk0C/KcTxosLRR9Lug9k4i', 'Vasya Oblomov', 'vasya@ideas4transfer.ru', '88003333333', 'WAREHOUSE', 1);

INSERT INTO command_project.brands_tbl(
    name, abbr)
VALUES ('Goods House Авеню', 'GDH');

INSERT INTO command_project.brands_tbl(
    name, abbr)
VALUES ('Some brand', 'ckh');

INSERT INTO command_project.shops_tbl(
    name, abbr)
VALUES ('Goods House Авеню', 'GHAVENU');

INSERT INTO command_project.shops_tbl(
    name, abbr)
VALUES ('ООО Какой-то магазин', 'anyabbr');

INSERT INTO command_project.warehouses_tbl(
    name, abbr)
VALUES ('Склад №1', 'skd1');


insert into command_project.users_brands_tbl (user_id, brand_id)
values (1, 2),
       (2, 1),
       (3, 2);


insert into command_project.brands_shops_tbl (brand_id, shop_id) VALUES
    (1, 1),
    (2, 2);





