drop table if exists products cascade;
create table products (id bigserial, title varchar(255), description varchar(5000), primary key(id));
insert into products
(title, description) values
('Cheese', 'Kamamber'),
('Apples', 'Green'),
('Bread', 'Chiabata');