package com.example.demo.Servicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import org.hibernate.boot.model.relational.Database;

import com.example.demo.DatabaseManager.DatabaseManager;
import com.example.demo.Entidades.Usuario;

public class CuentaServicio {

    private final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+.edu\\.co$";
    private String PASSWORD_PATTERN = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[.#?!@$ %^&*-]).{8,}$";

    public boolean validarCorreo(String correo) {
        return correo.matches(EMAIL_PATTERN);
    }
    public boolean validarContrasena(String contrasena) {
        return contrasena.matches(PASSWORD_PATTERN);
    }

    public boolean hasEmptyFields(Usuario usuario) {
        return (usuario.getCorreo() == null || usuario.getCorreo().isEmpty() || usuario.getNombre() == null || 
            usuario.getNombre().isEmpty() ||
            usuario.getRol() == null || usuario.getRol().isEmpty() ||
            usuario.getContrasena() == null || usuario.getContrasena().isEmpty());
    }

    public boolean crearUsuario(Usuario usuario)
        throws Exception{
            String u = "INSERT INTO Usuario (correo, nombre, contrasena, rol) VALUES (?, ?, ?, ?)";
            try (Connection conexion = DatabaseManager.getConnection();
            PreparedStatement pre = conexion.prepareStatement(u)){
                
                pre.setString(1, usuario.getCorreo());
                pre.setString(2, usuario.getNombre());
                pre.setString(3, usuario.getRol());
                pre.setString(4, usuario.getContrasena());
                int rowsAffected = pre.executeUpdate();
                
                return rowsAffected>0;

            }catch (SQLException e) {
                    throw new Exception ("Error al intentar crear el usuario");
            }
    }

    public String crearUsuario(String correo, String nombre, String contrasena, String rol)
    throws Exception{
        Usuario usuario = new Usuario();
        usuario.setCorreo(correo);
        usuario.setNombre(nombre);
        usuario.setRol(rol);
        usuario.setContrasena(contrasena);
        

        if (hasEmptyFields(usuario)){throw new Exception("Complete los campos por favor");}

        if (!validarCorreo(correo)){throw new Exception ("El correo no es valido");}

        if (!validarContrasena(contrasena)){throw new Exception("La contraseña debe contener una mayuscula, un numero y un caracter especial");}

        try{
            crearUsuario(usuario);
            return("cuenta creada correctamente");
        }catch (Exception e){
            throw new Exception ("Error al crear el usuario"+ e.getMessage(), e);
                }
    }

    //
    public boolean login (String correo, String contrasena){
        String u = "SELECT * FROM Usuario WHERE correo = ? AND contrasena = ?";
        
        try (Connection conexion = DatabaseManager.getConnection();
        PreparedStatement pre = conexion.prepareStatement(u)){
            pre.setString(1, correo);
            pre.setString(2, contrasena);

            try (ResultSet resp = pre.executeQuery()){
                if (resp.next()){
                    System.out.println("Bienvenido");
                    return true;
                }
            }catch(SQLException e){
                System.out.println("Error al iniciar sesión"+ e.getMessage());
            }
            return false;
        }
    }


    

}
