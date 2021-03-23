-- drop schema if exists command_project cascade;
create schema command_project;

set search_path  to command_project;

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
VALUES (1, '23.03.2021');

INSERT INTO delivery_time_tbl(
    id, delivery_time)
VALUES (2,'30.04.2021');

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
                                                                 'Е855ВА177', 'доставить без опазаданий', '04.30.2021',
                                                                 'Petro', '1126', '20', 'w123444',
                                                                 'ООО Редиска', 'w123444', 2, 2, 2, 2, 2, 1);
