--liquibase formatted sql
--changeset antoxakon:1  -- Формат: author:id
--comment: create table tasks

create table tasks
(
    id bigserial primary key,
    title varchar(255),
    description varchar(255),
    status varchar(255)
);