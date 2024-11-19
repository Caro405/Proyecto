package com.example.demo.Entidades;

import java.util.Date;

import lombok.Data;

@Data
public class Comunidad {

    private Long comunidadId;
    
    private String nombre;
    private String descripcion;

    private Date fechaCreacion;
    
    private String categoria;
   
}
