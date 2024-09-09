CREATE TABLE USERS (
    id SERIAL PRIMARY KEY,
    name VARCHAR(20),
    surname VARCHAR(20),
    email VARCHAR(25),
    username VARCHAR(10),
    password VARCHAR(15)
);

CREATE TABLE CONTACTS (
    id SERIAL,
    users_id INT REFERENCES USERS(id),
    name VARCHAR(20),
    surname VARCHAR(20),
    email VARCHAR(25),
    phone_number VARCHAR(12),
    description VARCHAR(74)
);