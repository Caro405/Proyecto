package com.example.demo.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Usuario {

    @Id
    Long id_usuario;
    String nombre;
    String correo;
    String contrasena;
    String rol;

    public Usuario() {}

    public Usuario(Long id_usuario, String nombre, String rol, String correo, String contrasena) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.rol = rol;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String mostrarUsuario(){
        return id_usuario + ", " + nombre + ", " + correo + ", " + contrasena + ", " + rol;
    }

}
