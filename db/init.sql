INSERT INTO bodytypes VALUES(1, 'Седан');
INSERT INTO bodytypes VALUES(2, 'Универсал');
INSERT INTO bodytypes VALUES(3, 'Хэтчбэк');
INSERT INTO bodytypes VALUES(4, 'Купе');
INSERT INTO bodytypes VALUES(5, 'Минивэн');
INSERT INTO bodytypes VALUES(6, 'Лифтбэк');

INSERT INTO carbrands VALUES(1, 'Audi');
INSERT INTO carbrands VALUES(2, 'BMW');
INSERT INTO carbrands VALUES(3, 'Mercedes-Benz');
INSERT INTO carbrands VALUES(4, 'Renault');

INSERT INTO carmodels VALUES(1, 'Q8', 1);
INSERT INTO carmodels VALUES(2, 'TT', 1);
INSERT INTO carmodels VALUES(3, 'R8', 1);
INSERT INTO carmodels VALUES(4, 'A8', 1);
INSERT INTO carmodels VALUES(5, '8 Series G15', 2);
INSERT INTO carmodels VALUES(6, 'X5 G05', 2);
INSERT INTO carmodels VALUES(7, 'X6 E71', 2);
INSERT INTO carmodels VALUES(8, 'M6 (F06/F13/F12)', 2);
INSERT INTO carmodels VALUES(9, 'S 680 GUARD 4MATIC', 3);
INSERT INTO carmodels VALUES(10, 'E 350d 4MATIC SportPlus', 3);
INSERT INTO carmodels VALUES(11, 'Mercedes-AMG G 63', 3);
INSERT INTO carmodels VALUES(12, 'A 200 Sport', 3);
INSERT INTO carmodels VALUES(13, 'DUSTER', 4);
INSERT INTO carmodels VALUES(14, 'KAPTUR', 4);
INSERT INTO carmodels VALUES(15, 'LOGAN', 4);
INSERT INTO carmodels VALUES(16, 'SANDERO', 4);

INSERT INTO users VALUES(3, 'root@local', 'Admin', 'root', '+79371111111');
INSERT INTO users VALUES(4, 'user_1@list.ru', 'User1', 'user 1', '+79372222222');

INSERT INTO posts VALUES(262, 2, 'Зеленый', {ts '2021-11-06 01:05:00.414'}, 'Классная тачка', 65000, '262.jpg', true, 3, 1, 1, 1, false);
INSERT INTO posts VALUES(263, 3, 'Черный', {ts '2021-11-06 01:06:59.255'}, 'Бумер', 23000, '263.jpg', false, 3, 3, 2, 7, false);
INSERT INTO posts VALUES(264, 5, 'Белый', {ts '2021-11-06 01:08:59.691'}, 'Гелик', 45000, '264.jpg', false, 4, 3, 3, 11, false);
INSERT INTO posts VALUES(265, 7, 'Синий', {ts '2021-11-06 01:10:22.650'}, 'Надежная экономная', 140000, '265.jpg', false, 4, 1, 4, 15, false);