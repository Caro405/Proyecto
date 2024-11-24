-- Elimina la base de datos si ya existe
DROP DATABASE IF EXISTS red;

-- Crea la base de datos
CREATE DATABASE IF NOT EXISTS red;
USE red;

-- Elimina las tablas en el orden correcto para evitar errores de dependencia
DROP TABLE IF EXISTS comentario;
DROP TABLE IF EXISTS usuario_comunidad;
DROP TABLE IF EXISTS publicacion;
DROP TABLE IF EXISTS archivo;
DROP TABLE IF EXISTS comunidad;
DROP TABLE IF EXISTS usuario;

-- Crear tabla Usuario
CREATE TABLE usuario (
    id_usuario INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    correo VARCHAR(100) NOT NULL UNIQUE, -- Un correo único por usuario
    nombre VARCHAR(100) NOT NULL,
    contrasena VARCHAR(100) NOT NULL,
    rol VARCHAR(50) NOT NULL DEFAULT 'usuario' -- El rol puede ser 'usuario', 'admin', etc.
);

-- Crear tabla Comunidad
CREATE TABLE comunidad (
    id_comunidad INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(300) NOT NULL,
    fecha_creacion DATE NOT NULL DEFAULT CURDATE(), -- Fecha de creación por defecto
    categoria VARCHAR(100) NOT NULL
);

-- Crear tabla Publicacion (depende de Usuario y Comunidad)
CREATE TABLE publicacion (
    id_publicacion INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descripcion TEXT,
    id_comunidad INT NOT NULL, -- Relación con Comunidad
    id_usuario INT NOT NULL, -- Relación con el usuario que creó la publicación
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_publicacion_comunidad FOREIGN KEY (id_comunidad) REFERENCES comunidad(id_comunidad) ON DELETE CASCADE,
    CONSTRAINT fk_publicacion_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE
);

-- Crear tabla Usuario_Comunidad (depende de Usuario y Comunidad)
CREATE TABLE usuario_comunidad (
    id_usuario INT NOT NULL,
    id_comunidad INT NOT NULL,
    fecha_union DATE NOT NULL DEFAULT CURDATE(), -- Fecha en la que el usuario se une a la comunidad
    PRIMARY KEY (id_usuario, id_comunidad), -- Llave primaria compuesta
    CONSTRAINT fk_usuario_comunidad_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE,
    CONSTRAINT fk_usuario_comunidad_comunidad FOREIGN KEY (id_comunidad) REFERENCES comunidad(id_comunidad) ON DELETE CASCADE
);

-- Crear tabla Archivo (tabla independiente)
CREATE TABLE archivo (
    id_archivo INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    tamano INT NOT NULL,
    fecha DATE NOT NULL DEFAULT CURDATE()
);

-- Crear tabla Comentario (depende de Publicacion y Usuario)
CREATE TABLE comentario (
    id_comentario INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    texto TEXT NOT NULL,
    id_publicacion INT NOT NULL, -- Relación con la Publicación
    id_usuario INT NOT NULL, -- Relación con el usuario que creó el comentario
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_comentario_publicacion FOREIGN KEY (id_publicacion) REFERENCES publicacion(id_publicacion) ON DELETE CASCADE,
    CONSTRAINT fk_comentario_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE
);

COMMIT;