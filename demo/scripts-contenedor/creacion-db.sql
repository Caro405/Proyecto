DROP DATABASE IF EXISTS red;
CREATE DATABASE IF NOT EXISTS red;
USE red;

SELECT 'CREATING DATABASE STRUCTURE' as 'INFO';

-- Eliminar tablas si existen
DROP TABLE IF EXISTS usuario,
                    comunidad,
                    archivo,
                    publicacion;

-- Crear tabla usuario
CREATE TABLE usuario (
    id_usuario INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    correo VARCHAR(100) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    contrasena VARCHAR(100) NOT NULL,
    rol VARCHAR(100)
);

-- Crear tabla comunidad
CREATE TABLE Comunidad (
    id_comunidad INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(300) NOT NULL,
    fechaCreacion DATE,
    categoria VARCHAR(100),
    id_creador INT NOT NULL, -- Relación con usuario
    CONSTRAINT fk_comunidad_usuario FOREIGN KEY (id_creador) REFERENCES usuario(id_usuario)
        ON DELETE CASCADE ON UPDATE CASCADE
);

-- Crear tabla archivo
CREATE TABLE archivo (
    id_archivo INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    tamano INT NOT NULL,
    fecha DATE,
    id_usuario INT NOT NULL, -- Relación con usuario
    CONSTRAINT fk_archivo_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
        ON DELETE CASCADE ON UPDATE CASCADE
);

-- Crear tabla publicacion
CREATE TABLE Publicacion (
    id_publicacion INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descripcion TEXT,
    id_comunidad INT NOT NULL, -- Relación con comunidad
    id_usuario INT NOT NULL, -- Relación con usuario
    CONSTRAINT fk_publicacion_comunidad FOREIGN KEY (id_comunidad) REFERENCES Comunidad(id_comunidad)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_publicacion_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
        ON DELETE CASCADE ON UPDATE CASCADE
);

