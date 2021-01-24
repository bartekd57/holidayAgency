insert into trip(id, counter, date_from, date_to, duration, adult_price, child_price, trip_type,  alimentation, description, status, people_limit, destination_id, img_url) VALUES
(1, 0,  '2021-01-20', '2020-01-30', 10, 120, 100, 'WINTER', 'AI', 'Zimowy wyjazd', 'ACTIVE', 20, 1, '/img/bt1.jpg'),
(2, 0, '2021-05-15', '2020-05-20', 5, 110, 90, 'SUMMER', 'HB', 'Fajny majowy urlop', 'ACTIVE', 30, 2, '/img/bt1.jpg'),
(3, 0, '2021-07-10', '2020-07-21', 11, 150, 130, 'ROUND_TRIP', 'FB', 'urlop wakacyjny', 'PROMOTED', 10, 3, '/img/bt1.jpg'),
(4, 0, '2021-04-7', '2020-04-14', 7, 90, 80, 'LAST_MINUTE', 'SC', 'Wyjazd wczesną wiosną', 'EXPIRED', 20, 4, '/img/bt1.jpg'),
(5, 0, '2021-09-8', '2020-09-16', 8, 129, 115, 'ROUND_TRIP', 'AI', 'Wypadzik wrześniowy', 'ACTIVE', 8, 5, '/img/bt1.jpg');



insert into address(id, road, road_number, postal_code, city, country) VALUES
(1, 'Marszałkowska', 12, '02-497', 'Warszawa', 'Polska'),
(2, 'Grójecka', 4, '01-437', 'Radom', 'Polska'),
(3, 'Strasse', 78, '21-233', 'Berlin', 'Niemcy'),
(4, 'Dmowskiego', 56, '12-784', 'Wrocław', 'Polska'),
(5, 'Morska', 6, '11-239', 'Gdańsk', 'Polska'),
(6, 'Zielona', 26, '01-439', 'Poznań', 'Polska'),
(7, 'Prosta', 61, '11-415', 'Sosnowiec', 'Polska'),
(8, 'Barska', 13, '02-239', 'Warszawa', 'Polska');

insert into destination(id, continent, country, city, airport) VALUES
(1, 'Europa', 'Szwajcaria', 'Zurych', 'Zurych-Kloten'),
(2, 'Europa', 'Hiszpania', 'Barcelona', 'El-Prat'),
(3, 'Azja', 'Tajlandia', 'Bangkok', 'Suvarnabhumi'),
(4, 'Ameryka Północna', 'Nowy Jork', 'Nowy Jork', 'F.Kennedego'),
(5, 'Azja', 'Indie', 'Bombaj', 'Shivaji');

insert into hotel(id, name, standard, location, description, destination_id) VALUES
(1, 'Hilton', 'FIVE_STAR', 'Zurych', 'Luksusowy hotel rodziny Hiltonów', 1),
(2, 'Sheraton', 'FOUR_STAR', 'Barcelona', 'Bardzo porządny hotel', 2),
(3, 'Marriot', 'FIVE_STAR', 'Bangkok', 'Amerykanski hotel dla bogatych', 3),
(4, 'Plaza', 'THREE_STAR', 'Nowy Jork', 'Hotel przy Central Parku', 4),
(5, 'Ibis', 'ONE_STAR', 'Bombaj', 'Hotel w wersji ekonomicznej', 5),
(6,  'Hotelik', 'THREE_STAR', 'Zurych', 'Fajny hotel', 1),
(7, 'Noclegownia', 'TWO_STAR', 'Bangkok', 'Dobry na tanie nocowanie', 3),
(8, 'Hotel Manhattan', 'FOUR_STAR', 'Nowy Jork', 'Dobre miejsce do nocowania w Nowym Jorku', 4);

insert into user(id, first_name, last_name, user_name, email, password, confirm_password, role_name, address_id) VALUES
(1, 'Jan','Kowalski', 'kowal', 'jan.kowalski@gmail.com', 'janek1234', 'janek1234','ROLE_USER', 1),
(2, 'Agata','Nowak', 'nowak', 'agata.nowak@gmail.com', 'agatka1234', 'agatka1234','ROLE_ADMIN', 2),
(3, 'Marian','Pawlak', 'pawlak', 'marian.pawlak@gmail.com', 'marian1234', 'marian1234','ROLE_USER', 3),
(4, 'Karolina','Pisarek', 'pisar', 'karolina.pisarek@gmail.com', 'karolina1234', 'karolina1234','ROLE_USER', 4),
(5, 'Stefan','Boczek', 'boczek', 'stefan.boczek@gmail.com', 'stefan1234', 'stefan1234','ROLE_ADMIN', 5),
(6, 'Anna','Szarek', 'szara', 'anna.szarek@gmail.com', 'anna1234', 'anna1234','ROLE_USER', 6),
(7, 'Karol','Szepietowski', 'szepieto', 'karol.szepietowski@gmail.com', 'karol1234', 'karol1234','ROLE_ADMIN', 7),
(8, 'Genowefa','Pigwa', 'pigwa', 'genowefa.pigwa@gmail.com', 'genowefa1234', 'genowefa1234','ROLE_USER', 8);

insert into roles (name) VALUES ('ROLE_USER'), ('ROLE_ADMIN');


insert into hotel_trip(hotel_id, trip_id) VALUES
(1,1),(6,1),(2,2),(3,3),(7,3),(4,4),(8,4),(5,5);

insert into user_trip(user_id, trip_id) VALUES
(1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8);














