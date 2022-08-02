create database if not exists myblog;

use myblog; 

create table if not exists posts(
	id BIGINT primary key not null auto_increment,
    content varchar(255) not null,
    description varchar(255) not null,
    title varchar(255) not null unique
);