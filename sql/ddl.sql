-- auto-generated definition
create table item
(
    id       bigint auto_increment
        primary key,
    price    int          null,
    title    varchar(255) null,
    username varchar(255) null,
    constraint FKa4fgi472e5yrrduvtsv2a13ar
        foreign key (username) references member (username)
);

-- auto-generated definition
create table member
(
    id           bigint auto_increment
        primary key,
    display_name varchar(255) not null,
    password     varchar(255) not null,
    username     varchar(255) not null,
    constraint UKgc3jmn7c2abyo3wf6syln5t2i
        unique (username)
);