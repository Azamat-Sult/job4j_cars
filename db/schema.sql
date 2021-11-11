CREATE TABLE if not exists bodytypes (
   id SERIAL PRIMARY KEY,
   bodytype VARCHAR(255)
);

CREATE TABLE if not exists carbrands (
   id SERIAL PRIMARY KEY,
   carbrand VARCHAR(255)
);

CREATE TABLE if not exists carmodels (
   id SERIAL PRIMARY KEY,
   carmodel VARCHAR(255),
   carbrand_id INT references carbrands(id)
);

CREATE TABLE if not exists users (
   id SERIAL PRIMARY KEY,
   email VARCHAR(255),
   name VARCHAR(255),
   password VARCHAR(255),
   phone VARCHAR(255)
);

CREATE TABLE if not exists posts (
   id SERIAL PRIMARY KEY,
   ageyears INT,
   bodycolor VARCHAR(255),
   created TIMESTAMP,
   description VARCHAR(255),
   mileage INT,
   photo VARCHAR(255),
   sold BOOLEAN,
   author_id INT references users(id),
   bodytype_id INT references bodytypes(id),
   carbrand_id INT references carbrands(id),
   carmodel_id INT references carmodels(id),
   showSoldButton BOOLEAN
);