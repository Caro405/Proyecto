package com.example.demo.Entidades;

import lombok.Data;

import java.sql.Date;

@Data
public class Archivo {
    private Long archivoId;
    private String nombreArchivo;
    private long tamano; // Tamaño en bytes
    private Date fechaSubida;

    public Archivo() {
    }

    public Archivo(String nombreArchivo, long tamano, Date fechaSubida) {
        this.nombreArchivo = nombreArchivo;
        this.tamano = tamano;
        this.fechaSubida = fechaSubida;
    }
}

