create table song(
                       id   int primary key auto_increment,
                       name varchar(20) not null
);

insert into song(name) values
('tuman'), ('Opyat 25'), ('Moskva-Piter'), ('svyat')



create table listening(
    person_id int references person(id),
    song_id int references song(id)
)

insert into listening values
    (1, 4), (2, 4), (2, 3), (3, 1)