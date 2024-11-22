package com.example.demo.Entidades;

import java.util.List;

import lombok.Data;

@Data
public class Publicacion {

    Long id;

    String titulo;
    String descripcion;

    List<Comentario> comentarios;

}
