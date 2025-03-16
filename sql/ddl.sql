create table item
(
    id          bigint auto_increment
        primary key,
    insert_date datetime(6)  null,
    price       int          null,
    title       varchar(255) null,
    update_date datetime(6)  null,
    username    varchar(255) null,
    constraint FKa4fgi472e5yrrduvtsv2a13ar
        foreign key (username) references member (username)
            on delete cascade
);

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

CREATE TABLE SPRING_SESSION (
    PRIMARY_ID CHAR(36) NOT NULL,
    SESSION_ID CHAR(36) NOT NULL,
    CREATION_TIME BIGINT NOT NULL,
    LAST_ACCESS_TIME BIGINT NOT NULL,
    MAX_INACTIVE_INTERVAL INT NOT NULL,
    EXPIRY_TIME BIGINT NOT NULL,
    PRINCIPAL_NAME VARCHAR(100),
    CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (PRIMARY_ID)
);

CREATE UNIQUE INDEX SPRING_SESSION_IX1 ON SPRING_SESSION (SESSION_ID);
CREATE INDEX SPRING_SESSION_IX2 ON SPRING_SESSION (EXPIRY_TIME);
CREATE INDEX SPRING_SESSION_IX3 ON SPRING_SESSION (PRINCIPAL_NAME);

CREATE TABLE SPRING_SESSION_ATTRIBUTES (
   SESSION_PRIMARY_ID CHAR(36) NOT NULL,
   ATTRIBUTE_NAME VARCHAR(200) NOT NULL,
   ATTRIBUTE_BYTES BLOB NOT NULL,
   CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
   CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES SPRING_SESSION(PRIMARY_ID) ON DELETE CASCADE
);