--liquibase formatted sql
--changeset antoxakon:3  -- Формат: author:id
--comment: create table employes

create table Department(
    id bigserial primary key ,
    department_name varchar(125) NOT NULL UNIQUE
);

create table employe
(
    id   bigserial primary key,
    name varchar(125) DEFAULT 'employe',
    position varchar(125) DEFAULT 'intern',
    deartment_id int ,
    FOREIGN KEY (deartment_id) references Department(id)
);

