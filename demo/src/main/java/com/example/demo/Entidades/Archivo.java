package com.example.demo.Entidades;


import java.sql.Date;
import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "archivo")
public class Archivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long archivoId;

    private String nombre;
    private String tipoDeSubida;
    private int tamano;

    @Column(name = "fecha_subida")
    private Date fechaSubida;

    public Archivo() {}
}
    