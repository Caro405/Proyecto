package com.example.demo.Servicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Service;

import com.example.demo.DatabaseManager.DatabaseManager;
import com.example.demo.Entidades.Usuario;

@Service
public class CuentaServicio {

    private final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@javeriana.edu.co$";
    private final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#\\$%\\^&\\*\\-_]).{8,}$";

    public boolean validarContrasena(String contrasena) {
        return contrasena.matches(PASSWORD_PATTERN);
    }

    public boolean validarCorreo(String correo) {
        return correo.matches(EMAIL_PATTERN);
    }

    public boolean hasEmptyFields(Usuario usuario) {
        return (usuario.getCorreo() == null || usuario.getCorreo().isEmpty() ||
                usuario.getNombre() == null || usuario.getNombre().isEmpty() ||
                usuario.getContrasena() == null || usuario.getContrasena().isEmpty() ||
                usuario.getRol() == null || usuario.getRol().isEmpty()
        );
    }

    public boolean crearUsuario(Usuario usuario) throws Exception {
        String sql = "INSERT INTO usuario (correo, nombre, rol, contrasena) VALUES (?, ?, ?, ?)";
        
        try (Connection conexion = DatabaseManager.getConnection();
             PreparedStatement pre = conexion.prepareStatement(sql)) {
             
            // Establece los parámetros en el orden correcto
            pre.setString(1, usuario.getCorreo());
            pre.setString(2, usuario.getNombre());
            pre.setString(3, usuario.getRol());
            pre.setString(4, usuario.getContrasena());

            int rowsAffected = pre.executeUpdate();
            System.out.println("Filas afectadas: " + rowsAffected);

            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error al intentar crear el usuario en la base de datos: " + e.getMessage());
            throw new Exception("Error al intentar crear el usuario en la base de datos", e);
        }
    }

    public String crearUsuario(String correo, String nombre, String contrasena, String rol) throws Exception {
        Usuario usuario = new Usuario();
        usuario.setCorreo(correo);
        usuario.setNombre(nombre);
        usuario.setRol(rol);
        usuario.setContrasena(contrasena);

        if (hasEmptyFields(usuario)) {
            throw new Exception("Complete todos los campos.");
        }

        if (!validarCorreo(correo)) {
            throw new Exception("El correo no es válido.");
        }

        if (!validarContrasena(contrasena)) {
            throw new Exception("La contraseña debe contener una mayúscula, un número y un carácter especial, y tener al menos 8 caracteres.");
        }

        try {
            if (crearUsuario(usuario)) {
                return "Cuenta creada correctamente.";
            } else {
                throw new Exception("No se pudo crear la cuenta. Intente nuevamente.");
            }
        } catch (Exception e) {
            throw new Exception("Error al crear el usuario: " + e.getMessage(), e);
        }
    }

    public boolean login(String correo, String contrasena) {
        String sql = "SELECT * FROM usuario WHERE correo = ? AND contrasena = ?";
        
        try (Connection conexion = DatabaseManager.getConnection();
             PreparedStatement pre = conexion.prepareStatement(sql)) {
             
            pre.setString(1, correo);
            pre.setString(2, contrasena);

            try (ResultSet resp = pre.executeQuery()) {
                if (resp.next()) {
                    System.out.println("Bienvenido");
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en el proceso de inicio de sesión: " + e.getMessage());
        }
        return false;
    }

    // Método adicional para verificar la conexión a la base de datos
    public boolean verificarConexion() {
        try (Connection conexion = DatabaseManager.getConnection()) {
            boolean isConnected = conexion != null && !conexion.isClosed();
            System.out.println("Conexión exitosa: " + isConnected);
            return isConnected;
        } catch (SQLException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
            return false;
        }
    }
}
