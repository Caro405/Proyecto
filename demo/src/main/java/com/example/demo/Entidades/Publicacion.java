package com.example.demo.Entidades;

import lombok.Data;

@Data
public class Publicacion {

    private Long id_publicacion;
    private String titulo;
    private String descripcion;
    private Long idComunidad;
}
