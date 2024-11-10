package com.example.demo.Entidades;

//import java.util.Date;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Comunidad {

    @Id
    @GeneratedValue
    private Long comunidadId;
    
    /*
    private String nombre;
    private String descripcion;
    private Date fechaCreacion;
    private String categoria;
    */
}
