create table item(
                     id int primary key auto_increment,
                     person_id int references person(id) on delete set null,
                     name varchar(50) not null
);

insert into item(person_id, name) values
                                      (1, 'Airpods Pro'), (1, 'JBL Go 3'), (2, 'iPhone 11'),
                                      (2, 'Apple Watch'), (3, 'Xbox');