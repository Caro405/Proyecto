package com.example.demo.Entidades;

//import java.util.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Archivo {
    @Id
    @GeneratedValue
    private Long archivoId;
/*
    private String nombre;
    private String tipoDeSubida;
    private int tamano;
    private Date fechaSubida;
*/

    // Constructor vacío
    public Archivo() {};
}
