package com.example.demo.Entidades;

import java.util.Date;

import lombok.Data;

@Data
public class Comunidad {

    Long comunidadId;
    
    String nombre;
    String descripcion;
    Date fechaCreacion;
    String categoria;

}
