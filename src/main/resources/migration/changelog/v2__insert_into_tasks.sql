--liquibase formatted sql
--changeset antoxakon:2  -- Формат: author:id
--comment: добавил данные

insert into tasks (id, title, description, status)
 VALUES (2, 'dsa','asd','NEW');