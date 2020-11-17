create table users(
id varchar2(8) primary key,
password varchar2(8) not null,
name varchar2(30) not null,
role varchar(5) default 'USER'
);