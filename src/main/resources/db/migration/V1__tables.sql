create table studio (
    id int auto_increment primary key,
    name varchar(100) not null,
    constraint unique_studio_name unique (name)
);

create table producer (
    id int auto_increment primary key,
    name varchar(100) not null,
    constraint unique_producer_name unique (name)
);

create table movies (
    id int auto_increment primary key,
    year_release int not null,
    title varchar(100) not null,
    winner boolean not null default false
);

create table movie_studio (
    id int auto_increment primary key,
    movie_id int not null,
    studio_id int not null,
    foreign key (movie_id) references movies(id),
    foreign key (studio_id) references studio(id),
    constraint unique_movie_studio unique (movie_id, studio_id)
);

create table movie_producer (
    id int auto_increment primary key,
    movie_id int not null,
    producer_id int not null,
    foreign key (movie_id) references movies(id),
    foreign key (producer_id) references producer(id),
    constraint unique_movie_producer unique (movie_id, producer_id)
);