-- Eliminar la base de datos si existe y crearla nuevamente
DROP DATABASE IF EXISTS red;
CREATE DATABASE IF NOT EXISTS red;
USE red;

-- Mostrar información de la creación
SELECT 'CREATING DATABASE STRUCTURE' as 'INFO';

-- Eliminar las tablas existentes si ya están creadas
DROP TABLE IF EXISTS usuario,
                    comunidad,
                    archivo,
                    publicacion,
                    usuario_comunidad,
                    comentario;

-- Crear tabla Usuario
CREATE TABLE usuario (
    id_usuario INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    correo VARCHAR(100) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    contrasena VARCHAR(100) NOT NULL,
    rol VARCHAR(100)
);

-- Crear tabla Comunidad
CREATE TABLE comunidad (
    id_comunidad INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(300) NOT NULL,
    fecha_creacion DATE NOT NULL DEFAULT CURDATE(), -- Fecha por defecto actual
    categoria VARCHAR(100) NOT NULL
);

-- Crear tabla Usuario_Comunidad (relación muchos a muchos entre Usuario y Comunidad)
CREATE TABLE usuario_comunidad (
    id_usuario INT NOT NULL,
    id_comunidad INT NOT NULL,
    fecha_union DATE NOT NULL, -- Sin valor por defecto
    PRIMARY KEY (id_usuario, id_comunidad),
    CONSTRAINT fk_usuario_comunidad_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE,
    CONSTRAINT fk_usuario_comunidad_comunidad FOREIGN KEY (id_comunidad) REFERENCES comunidad(id_comunidad) ON DELETE CASCADE
);

-- Crear tabla Archivo
CREATE TABLE archivo (
    id_archivo INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    tamano INT NOT NULL,
    fecha DATE
);

-- Crear tabla Publicacion
CREATE TABLE publicacion (
    id_publicacion INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descripcion TEXT
);

-- Crear tabla Comentario
CREATE TABLE comentario (
    id_comentario INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    texto TEXT NOT NULL,
    id_publicacion INT NOT NULL, -- Relación con la Publicación
    id_usuario INT NOT NULL, -- Relación con el usuario que creó el comentario
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_comentario_publicacion FOREIGN KEY (id_publicacion) REFERENCES publicacion(id_publicacion) ON DELETE CASCADE,
    CONSTRAINT fk_comentario_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE
);
