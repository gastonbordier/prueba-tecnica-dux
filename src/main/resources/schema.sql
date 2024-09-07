CREATE SCHEMA IF NOT EXISTS dux;

CREATE TABLE dux.equipos (
    id serial PRIMARY KEY,
    nombre varchar(100) NULL,
    liga varchar(100) NULL,
    pais varchar(100) NULL
);