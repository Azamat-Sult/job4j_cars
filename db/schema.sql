CREATE TABLE if not exists users (
   id SERIAL PRIMARY KEY,
   email TEXT UNIQUE,
   name TEXT,
   password TEXT
);

CREATE TABLE if not exists posts (
   id SERIAL PRIMARY KEY,
   carBrand TEXT,
   bodyType TEXT,
   photo TEXT,
   description TEXT,
   sold BOOLEAN,
   author_id int not null references users(id)
);