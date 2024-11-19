package com.example.demo.Entidades;

import lombok.Data;

@Data
public class Usuario {

    Long id_usuario;

    String nombre;
    String correo;
    String rol;
    String contrasena;

    public Usuario() {};


}
