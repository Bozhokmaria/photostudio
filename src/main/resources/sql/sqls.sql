use photostudio;

INSERT INTO country (country_id, country_name)
VALUES  (1, 'Ukraine'),
        (2, 'Poland');

INSERT INTO city (city_id, city_name, country_id) 
VALUES (1, 'Kiev', 1),
       (2, 'Warsaw', 2);

INSERT INTO location (loc_id, loc_address_line, loc_city_id)
 VALUES (1, 'ul. Khreshchatyk, 21', 1),
        (2, 'ul. Krochmalna, 50', 2);

INSERT INTO photostudio (ph_st_id, ph_st_name, ph_st_loc_id) 
VALUES (1, 'Photo studio 01', 1),
	   (2, 'Photo studio 02', 2);

INSERT INTO dressing_room (dress_room_id, dress_room_name, dress_room_price, dress_room_ph_st) 
VALUES (1, 'Usual', 50.00, 1),
       (2, 'Luxury', 150.00, 1),
       (3, 'Simple', 250.00, 2),
       (4, 'All-inclusive', 550.00, 2),
       (5, 'All-inclusive', 550.00, 2);

INSERT INTO photographer (ph_id, ph_first_name, ph_last_name, ph_price) 
VALUES (1, 'Petr', 'Petrov', 500.00),
       (2, 'Max', 'Maksimov', 700.00),
       (3, 'Alina', 'Mouse', 500.00),
       (4, 'Victoria', 'Daily', 500.00),
       (5, 'Iren', 'Iren', 500.00);

INSERT INTO visagiste (vs_id, vs_first_name, vs_last_name, vs_price) 
VALUES (1, 'Mark', 'Marks', 350.00),
       (2, 'Martina', 'Marks', 400.00),
	   (3, 'Maksim', 'Marks', 450.00),
       (4, 'Vika', 'Dark', 450.00),
       (5, 'Anastasia', 'Clock', 50.00),
       (6, 'Yana', 'Kovt', 350.00),
	   (7, 'Kate', 'Kami', 350.00);

INSERT INTO hairdresser (hd_id, hd_first_name, hd_last_name, hd_price) 
VALUES (1, 'Natali', 'Black', 250.00),
		(2, 'Ivanna', 'Ivanova', 150.00),
		(3, 'Yana', 'Grey', 200.00),
        (4, 'Galyna', 'Dilan', 350.00),
        (5, 'Darina', 'Hairlove', 50.00),
        (6, 'Sasha', 'Bilan', 550.00),
        (7, 'Dima', 'Vik', 350.00);

INSERT INTO studio (studio_id, studio_ph_st_id, studio_name, studio_price) 
VALUES (1, 1, 'Rose room', 150.00),
       (2, 1, 'Work interior', 200.00);

INSERT INTO rate (rate_id, rate_name, rate_percentage) 
VALUES (1, 'Simple', 0.00),
       (2, 'Weekend', 10.00);

INSERT INTO client (client_id, client_email, client_first_name, client_last_name, client_password) 
VALUES (1, 'test@gmail.com', 'Mariia', 'Bozhok', 'passw123'),
		(2, 'test01@gmail.com', 'Mark', 'Block', 'passw1234'),
        (3, 'test02@gmail.com', 'Marta', 'Black', 'passw234'),
        (4, 'test03@gmail.com', 'Max', 'Dawn', 'passw34');

INSERT INTO abonement (abon_id, client_id, abon_name, abon_photosession_amount, abon_price) 
VALUES (1, 1, 'Monthly', 8, 3000.00),
		(2, 2, 'Monthly', 8, 3000.00);

INSERT INTO photostudio_visagiste (ph_vs_id, ph_st_id, vs_id) 
VALUES (1, 1, 1),
		(2, 1, 2);

INSERT INTO photostudio_hairdresser (ph_hd_id, ph_st_id, hd_id) 
VALUES (1, 1, 1),
		(2, 1, 2);

INSERT INTO photostudio_photographer (ph_ph_id, ph_st_id, ph_id)
VALUES (1, 1, 1),
		(2, 1, 2);

INSERT INTO `order` (order_id, order_booking, client_id, dress_room_id, studio_id, ph_id, vs_id, hd_id, rate_id, ph_st_id, order_total_price) 
VALUES (1, '2022-09-06 17:30:08', 1, 1, 2, 2, 1, 1, 1, 1, 1150.00),
(2, '2022-09-16 17:30:08', 1, 1, 2, 2, 1, 1, 1, 1, 1150.00),
(3, '2022-09-11 18:00:00', 2, 1, 2, 2, 1, 1, 2, 1, 1265.00);


UPDATE client
SET client_email = 'newemail@gmail.com', client_password = 'newpassword'
WHERE client_id = 1;

UPDATE client
SET client_first_name = 'Maria'
WHERE client_last_name like 'Boz%';

UPDATE client
SET client_last_name = 'Bozhok'
WHERE client_last_name like 'Boz%';

UPDATE photographer
SET ph_price = 200
WHERE ph_first_name like 'Ali%';

UPDATE hairdresser
SET hd_price = 666
WHERE hd_id =5;

SELECT * FROM dressing_room;
SELECT * FROM photographer WHERE ph_first_name IN ('Iren', 'Max');
SELECT * FROM hairdresser WHERE hd_price BETWEEN 100 AND 400 ORDER BY hd_price DESC;
SELECT * FROM hairdresser WHERE hd_first_name = 'Galyna' AND hd_last_name = 'Dilan';
SELECT * FROM client WHERE client_first_name = 'Mariia' OR client_first_name = 'Mark' OR client_first_name = 'Max';
SELECT COUNT(vs_id), vs_price FROM visagiste GROUP BY vs_price ORDER BY vs_price DESC;

DELETE FROM hairdresser WHERE hd_id =5;
DELETE FROM hairdresser WHERE hd_id =4;
DELETE FROM dressing_room WHERE dress_room_name = 'All-inclusive';
DELETE FROM photographer WHERE ph_first_name ='Iren';
DELETE FROM client WHERE client_last_name ='Dawn';


SELECT client.client_id, client_first_name, client_last_name, order_booking, order_total_price
FROM client
INNER JOIN photostudio.order ON client.client_id=photostudio.order.client_id;

SELECT client.client_id, client_first_name, client_last_name, order_booking, order_total_price
FROM client
LEFT JOIN photostudio.order
ON client.client_id=photostudio.order.client_id;

SELECT visagiste.vs_id, visagiste.vs_first_name, visagiste.vs_last_name, order_booking, order_total_price
FROM visagiste
LEFT JOIN photostudio.order
ON photostudio.order.vs_id=visagiste.vs_id;

SELECT visagiste.vs_id, visagiste.vs_first_name, visagiste.vs_last_name, order_booking, order_total_price
FROM visagiste
Right JOIN photostudio.order
ON photostudio.order.vs_id=visagiste.vs_id;

