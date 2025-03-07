-- auto-generated definition
create table item
(
    id    bigint auto_increment
        primary key,
    price int          null,
    title varchar(255) null
);

-- auto-generated definition
create table notice
(
    id    bigint auto_increment
        primary key,
    date  date         null,
    title varchar(255) null
);



