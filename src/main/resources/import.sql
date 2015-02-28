insert into movies (title, description) values('The Hobbit: An Unexpected Journey (2012)', 'A reluctant hobbit, Bilbo Baggins, sets out to the Lonely Mountain with a spirited group of dwarves to reclaim their mountain home - and the gold within it - from the dragon Smaug.');
insert into movies (title, description) values('The Hobbit: The Desolation of Smaug (2013)', 'The dwarves, along with Bilbo Baggins and Gandalf the Grey, continue their quest to reclaim Erebor, their homeland, from Smaug. Bilbo Baggins is in possession of a mysterious and magical ring.');
insert into movies (title, description) values('The Hobbit: The Battle of the Five Armies (2014)', 'Bilbo and Company are forced to engage in a war against an array of combatants and keep the Lonely Mountain from falling into the hands of a rising darkness.');

insert into cinemahalls (`key`, `seats`) values('A', 10);
insert into cinemahalls (`key`, `seats`) values('B', 60);
insert into cinemahalls (`key`, `seats`) values('C', 60);
insert into cinemahalls (`key`, `seats`) values('D', 10);
insert into cinemahalls (`key`, `seats`) values('E', 10);

insert into movieplays (movie_id, cinema_hall_key, play_date) values(1, 'A', CURRENT_DATE() || ' 21:00:00');
insert into movieplays (movie_id, cinema_hall_key, play_date) values(1, 'B', CURRENT_DATE() || ' 22:00:00');
insert into movieplays (movie_id, cinema_hall_key, play_date) values(1, 'C', CURRENT_DATE() || ' 23:00:00');
insert into movieplays (movie_id, cinema_hall_key, play_date) values(2, 'A', CURRENT_DATE() || ' 15:00:00');
insert into movieplays (movie_id, cinema_hall_key, play_date) values(2, 'B', CURRENT_DATE() || ' 18:00:00');
insert into movieplays (movie_id, cinema_hall_key, play_date) values(3, 'D', CURRENT_DATE() || ' 14:00:00');
insert into movieplays (movie_id, cinema_hall_key, play_date) values(3, 'E', CURRENT_DATE() || ' 18:00:00');

insert into movieplayreservations (reservation_number, movie_play_id, seats_taken, email) values('RESERV123', 1, 6, 'nonexistent@example.org');

create table IF NOT EXISTS users(username varchar_ignorecase(50) not null primary key,password varchar_ignorecase(500) not null,enabled boolean not null);
create table IF NOT EXISTS authorities (username varchar_ignorecase(50) not null,authority varchar_ignorecase(50) not null,constraint fk_authorities_users foreign key(username) references users(username));

insert into users(username, password, enabled) values ('admin', '$2a$10$x74Qtjoj1rgDzYxbIUokyeoqBxxpyMuhuyDWCaer2cq5cNwF2ufEa', true);
insert into authorities(username, authority) values('admin', 'ROLE_ADMIN');