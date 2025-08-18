--liquibase formatted sql
--changeset antoxakon:4  -- Формат: author:id
--comment: inserts

insert into Department (department_name) VALUES ('IT'),
                                                ('Market'),
                                                ('HR');

insert into employe (name, deartment_id) VALUES ('Anton', 1);
insert into employe (name, position, deartment_id) VALUES ('Kika', 'recruter', 3);

