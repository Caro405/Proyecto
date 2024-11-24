package com.example.demo.Entidades;

import lombok.Data;

@Data
public class Usuario {

    private Long id_usuario; // Declarar como privado
    private String nombre;
    private String correo;
    private String rol;
    private String contrasena;

    // Constructor vacío (opcional con @Data)
    public Usuario() {}
}
