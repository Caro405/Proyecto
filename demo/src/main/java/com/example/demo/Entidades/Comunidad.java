package com.example.demo.Entidades;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Comunidad {

    Long id_comunidad;
    
    String nombre;
    String descripcion;
    Date fechaCreacion;
    String categoria;

    List<Publicacion> publicaciones;


}
