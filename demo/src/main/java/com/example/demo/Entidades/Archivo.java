package com.example.demo.Entidades;

import java.util.Date;

public class Archivo {
    private Long archivoId;
    private String nombre;
    private String tipoDeSubida;
    private int tamano;
    private Date fechaSubida;

    // Constructor vacío
    public Archivo() {}

    // Getters y Setters
    public Long getArchivoId() {
        return archivoId;
    }

    public void setArchivoId(Long archivoId) {
        this.archivoId = archivoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoDeSubida() {
        return tipoDeSubida;
    }

    public void setTipoDeSubida(String tipoDeSubida) {
        this.tipoDeSubida = tipoDeSubida;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public Date getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(Date fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

    // Métodos adicionales según el diagrama
    public void guardarArchivo() {
        // Lógica para guardar el archivo
    }

    public void eliminarArchivo() {
        // Lógica para eliminar el archivo
    }
}
