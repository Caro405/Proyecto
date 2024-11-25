package com.example.demo.Entidades;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class Comunidad {

    private Long id_comunidad;
    private String nombre;
    private String descripcion;
    private Date fechaCreacion;
    private String categoria;
    
    private List<Publicacion> publicaciones;

}
