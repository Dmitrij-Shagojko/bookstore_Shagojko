
--DROP TABLE IF EXISTS books;
--DROP TABLE IF EXISTS languages;
--DROP TABLE IF EXISTS users;
--DROP TABLE IF EXISTS roles;

CREATE TABLE IF NOT EXISTS languages (
id SERIAL PRIMARY KEY,
name VARCHAR(30) UNIQUE NOT NULL);

CREATE TABLE IF NOT EXISTS books (
id BIGSERIAL PRIMARY KEY,
name VARCHAR(100) NOT NULL,
author VARCHAR (80) NOT NULL,
publicher VARCHAR (60) NOT NULL,
publishment_date DATE NOT NULL,
price DECIMAL (7, 2) NOT NULL,
paperback SMALLINT NOT NULL,
"ISBN-10" CHAR (10) UNIQUE,
"ISBN-13" CHAR (14) UNIQUE,
lexile_measure VARCHAR (10) NOT NULL,
weight SMALLINT NOT NULL,
dimensions VARCHAR (30) NOT NULL,
bestSellersRank VARCHAR (7) NOT NULL,
language_id SMALLINT NOT NULL REFERENCES languages);

CREATE TABLE IF NOT EXISTS roles (
id SERIAL PRIMARY KEY,
role VARCHAR(50) NOT NULL);

CREATE TABLE IF NOT EXISTS users (
id BIGSERIAL PRIMARY KEY,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(100) NOT NULL,
email VARCHAR(100) UNIQUE NOT NULL,
role_id SMALLINT NOT NULL REFERENCES roles);