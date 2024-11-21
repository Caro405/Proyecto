package com.example.demo.Servicio;

//import org.springframework.beans.factory.annotation.Autowired;
/*
import com.example.demo.Entidades.Archivo;
import com.example.demo.Entidades.Usuario;
import java.util.List;
*/

import org.springframework.stereotype.Service;
import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.example.demo.DatabaseManager.DatabaseManager;
import com.example.demo.Entidades.Comunidad;

@Service
public class ComunidadService {

    public boolean hasEmptyFields(Comunidad comunidad) {
        return (comunidad.getNombre() == null || comunidad.getNombre().isEmpty() ||
        comunidad.getDescripcion() == null || comunidad.getDescripcion().isEmpty() ||
        comunidad.getCategoria() == null ||  comunidad.getCategoria().isEmpty());
    }

    public boolean crearComunidad (Comunidad comunidad) throws Exception {
        String sql = "INSERT INTO Comunidad (nombre, descripcion, fechaCreacion, categoria) VALUES (?, ?, ?, ?)";
        
        try (Connection conexion = DatabaseManager.getConnection();
             PreparedStatement pre = conexion.prepareStatement(sql)) {
             
            // Establece los parámetros en el orden correcto
            pre.setString(1, comunidad.getNombre());
            pre.setString(2, comunidad.getDescripcion());
            pre.setDate(3, new java.sql.Date(comunidad.getFechaCreacion().getTime()));
            pre.setString(4, comunidad.getCategoria());

            int rowsAffected = pre.executeUpdate();
            System.out.println("Filas afectadas: " + rowsAffected);

            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error al intentar crear el Comunidad en la base de datos: " + e.getMessage());
            throw new Exception("Error al intentar crear el Comunidad en la base de datos", e);
        }
    }

    public String crearComunidad (String nombre, String descripcion, Date fechaCreacion, String categoria) throws Exception {
        Comunidad comunidad = new Comunidad();
        comunidad.setNombre(nombre);
        comunidad.setFechaCreacion(new Date());
        comunidad.setFechaCreacion(fechaCreacion);
        comunidad.setCategoria(categoria);

        if (hasEmptyFields(comunidad)) {
            throw new Exception("Complete todos los campos.");
        }

        try {
            if (crearComunidad(comunidad)) {
                return "Comunidad creada correctamente.";
            } else {
                throw new Exception("No se pudo crear la Comunidad. Intente nuevamente.");
            }
        } catch (Exception e) {
            throw new Exception("Error al crear la comunidad: " + e.getMessage(), e);
        }
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
