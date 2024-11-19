DROP DATABASE IF EXISTS red;
CREATE DATABASE IF NOT EXISTS red;
USE red;

SELECT 'CREATING DATABASE STRUCTURE' as 'INFO';

DROP TABLE IF EXISTS usuario,
                    comunidad,
                    archivo,
                    publicacion;

CREATE TABLE usuario(
    usuario_id INT AUTO_INCREMENT PRIMARY KEY,
    correo VARCHAR (100) NOT NULL,
    nombre VARCHAR (100) NOT NULL,
    rol VARCHAR (100)
);

CREATE TABLE comunidad(
    comunidad_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR (100) NOT NULL,
    descripcion VARCHAR (300) NOT NULL,
    fechaCreacion DATE,
    categoría VARCHAR (100)
);

CREATE TABLE archivo(
    archivo_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR (100) NOT NULL,
    tamano NUMBER (100) NOT NULL,
    date VARCHAR (100)
);

CREATE TABLE publicacion(
    publicacion_id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR (100) NOT NULL,
    descripcion TEXT
);
