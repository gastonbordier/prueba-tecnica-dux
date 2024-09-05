CREATE SCHEMA IF NOT EXISTS dux;

CREATE TABLE dux.equipos (
    id int8 NOT NULL,
    nombre varchar(100) NULL,
    liga varchar(100) NULL,
    pais varchar(100) NULL,
    CONSTRAINT equipos_pkey PRIMARY KEY (id)
);