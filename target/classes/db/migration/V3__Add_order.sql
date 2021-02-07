insert into orders_tbl (id, sender, receiver, created, order_status, user_id)
    values (999, 'John', 'Alex', TIMESTAMP '2020-12-21 23:00:00.00', 'NEW', 1002);

insert into order_details_tbl (id, number_of_boxes, comment, order_id)
    values (999, 23, 'Тестовый заказ от John', 999);

insert into shipment_details_tbl (id, driver_name, car_number,
                shipment_date, delivery_date, order_id)
    values (999, 'Иванов Иван Иванович', 'А111ААА777',
                TIMESTAMP '2020-12-24 13:00:00.00', TIMESTAMP '2020-12-24 22:00:00.00', 999)
