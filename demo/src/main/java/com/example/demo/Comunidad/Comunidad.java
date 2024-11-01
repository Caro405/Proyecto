package com.example.demo.Comunidad;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comunidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comunidadId;

    private String nombre;
    private String descripcion;

    @Temporal(TemporalType.DATE) // Especifica que se almacenará solo la fecha
    private Date fechaCreacion;

    private String categoria;

    // Getters y Setters
    public Long getComunidadId() {
        return comunidadId;
    }

    public void setComunidadId(Long comunidadId) {
        this.comunidadId = comunidadId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // Métodos adicionales según el diagrama
    public void agregarUsuario() {
        // Lógica para agregar un usuario a la comunidad
    }

    public void eliminarUsuario() {
        // Lógica para eliminar un usuario de la comunidad
    }

    public void publicarArchivo() {
        // Lógica para publicar un archivo en la comunidad
    }
}

