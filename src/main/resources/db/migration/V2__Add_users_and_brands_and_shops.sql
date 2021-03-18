insert into users_tbl (username, password, email, role)
values ('Alex', '$2y$12$xirQ1vo.wozECP4rXSMzFuKLU6oGHVOBk0C/KcTxosLRR9Lug9k4i', 'alex@ideas4transfer.ru', 'ADMIN');

insert into users_tbl (username, password, email, role)
values ('John', '$2y$12$xirQ1vo.wozECP4rXSMzFuKLU6oGHVOBk0C/KcTxosLRR9Lug9k4i', 'john@ideas4transfer.ru', 'BRAND_MANAGER');

insert into users_tbl (username, password, email, role)
values ('Vasya', '$2y$12$xirQ1vo.wozECP4rXSMzFuKLU6oGHVOBk0C/KcTxosLRR9Lug9k4i', 'vasya@ideas4transfer.ru', 'WAREHOUSE');

INSERT INTO brands_tbl(
    name, abbr)
VALUES ('Goods House Авеню', 'GDH');

INSERT INTO brands_tbl(
    name, abbr)
VALUES ('Some brand', 'ckh');

INSERT INTO shops_tbl(
    name, abbr)
VALUES ('Goods House Авеню', 'GHAVENU');


INSERT INTO shops_tbl(
    name, abbr)
VALUES ('ООО Какой-то магазин', 'anyabbr');

INSERT INTO warehouses_tbl(
    name, abbr)
VALUES ('Склад №1', 'skd1');
