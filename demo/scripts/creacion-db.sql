DROP DATABASE IF EXISTS red;
CREATE DATABASE IF NOT EXISTS red;
USE red;

SELECT 'CREATING DATABASE STRUCTURE' as 'INFO';

DROP TABLE IF EXISTS usuario,
                    comunidad,
                    archivo,
                    publicacion;

CREATE TABLE usuario (
    id_usuario INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
    correo VARCHAR (100) NOT NULL,
    nombre VARCHAR (100) NOT NULL,
    contrasena VARCHAR (100) NOT NULL,
    rol VARCHAR (100)
);

CREATE TABLE Comunidad (
    id_comunidad INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR (100) NOT NULL,
    descripcion VARCHAR (300) NOT NULL,
    fechaCreacion DATE,
    categoria VARCHAR (100)
);

CREATE TABLE archivo (
    id_archivo INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR (100) NOT NULL,
    tamano INT NOT NULL,
    fecha DATE
);

CREATE TABLE Publicacion (
    id_publicacion INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR (100) NOT NULL,
    descripcion TEXT,
    id_comunidad INT NOT NULL,
    CONSTRAINT fk_publicacion_comunidad FOREIGN KEY (id_comunidad) REFERENCES Comunidad(id_comunidad)
);

