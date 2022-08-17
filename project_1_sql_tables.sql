create table users (
user_id serial primary key,
first_name varchar (100),
last_name varchar (100),
user_pass varchar (100),
user_admin bool default 'f',
email_address varchar (100)); 

create table requests (
request_id serial primary key,
user_id int references users(user_id),
reason_for_reimbursment varchar (1000),
reason_for_request varchar(1000),
amount_requested decimal,
approved_denied bool);


insert into users (first_name, last_name, user_pass, user_admin, email_address)
values ('bob', 'dabuilder', 'fixit', 'f', 'bobd@gmail.com');

alter table users alter column email_address set not null;
alter table users alter column user_pass set not null;
alter table requests alter column reason_for_request set not null;
alter table requests alter column amount_requested set not null;
alter table requests alter column user_id set not null;
