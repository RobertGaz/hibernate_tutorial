create table passport(
                       person_id int primary key references person(id) on delete cascade,
                       passport_number int not null
);

insert into passport(person_id,passport_number) values
(1, 1601), (2, 1602), (3, 1603)
