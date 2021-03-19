-- create schema command_project;
set search_path to command_project;

CREATE TABLE users_tbl
(
    id bigserial,
    username character varying(255),
    password character varying(255),
    full_name character varying(255),
    email character varying(255),
    phone character varying(255),
    role character varying(255),
    warehouse_id bigint
);

CREATE TABLE brands_tbl
(
    id bigserial,
    name character varying(255),
    abbr character varying(255),
    shop_id bigint
);

CREATE TABLE users_brands
(
    user_id bigint,
    brand_id bigint,
    warehouse_id bigint
);

CREATE TABLE deliveries_tbl
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

CREATE TABLE shops_tbl
(
    id bigserial,
    name character varying(255),
    abbr character varying(255),
    brand_id bigint
);

CREATE TABLE warehouses_tbl
(
    id bigserial,
    name character varying(255),
    abbr character varying(255)
);


