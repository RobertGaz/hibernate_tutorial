create table person(
                       id   int primary key auto_increment,
                       name varchar(20) not null,
                       age  int check (age > 0)
);

insert into person(name,age) values
('Robert', 23), ('Alina', 24), ('Rustam', 24)
