package com.example.demo.Entidades;

import java.util.List;

import lombok.Data;

@Data
public class Publicacion {

    Long id_publicacion;

    String titulo;
    String descripcion;

    List<Comentario> comentarios;

    public void setId(Long id) {
        this.id_publicacion = id;
    }

    public Long getId() {
        return id_publicacion;
    }

}
