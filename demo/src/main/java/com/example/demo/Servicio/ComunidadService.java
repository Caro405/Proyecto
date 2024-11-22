package com.example.demo.Servicio;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;

import com.example.demo.DatabaseManager.DatabaseManager;
import com.example.demo.Entidades.Comunidad;

@Service
public class ComunidadService {

    public boolean hasEmptyFields(Comunidad comunidad) {
        return (comunidad.getNombre() == null || comunidad.getNombre().isEmpty() ||
                comunidad.getDescripcion() == null || comunidad.getDescripcion().isEmpty() ||
                comunidad.getCategoria() == null || comunidad.getCategoria().isEmpty());
    }


      public Comunidad crearComunidad(Comunidad comunidad) throws Exception {
    String sql = "INSERT INTO Comunidad (nombre, descripcion, fechaCreacion, categoria) VALUES (?, ?, ?, ?)";

    try (Connection conexion = DatabaseManager.getConnection();
         PreparedStatement pre = conexion.prepareStatement(sql)) {

        pre.setString(1, comunidad.getNombre());
        pre.setString(2, comunidad.getDescripcion());
        pre.setDate(3, new java.sql.Date(comunidad.getFechaCreacion().getTime()));
        pre.setString(4, comunidad.getCategoria());

        int rowsAffected = pre.executeUpdate();
        if (rowsAffected == 0) {
            throw new Exception("No se pudo crear la comunidad.");
        }
        return comunidad;
    } catch (SQLException e) {
        throw new Exception("Error al intentar crear la comunidad en la base de datos.", e);
    }
}


//obtener comunidades
public List<Comunidad> obtenerTodas() {
    List<Comunidad> comunidades = new ArrayList<>();
    String sql = "SELECT * FROM Comunidad";

    try (Connection conexion = DatabaseManager.getConnection();
        Statement stmt = conexion.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            Comunidad comunidad = new Comunidad();
            comunidad.setId_comunidad(rs.getLong("id_comunidad"));
            comunidad.setNombre(rs.getString("nombre"));
            comunidad.setDescripcion(rs.getString("descripcion"));
            comunidad.setFechaCreacion(rs.getDate("fechaCreacion"));
            comunidad.setCategoria(rs.getString("categoria"));

            comunidades.add(comunidad);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return comunidades;
}

//Buscar una comunidad por id

public Comunidad obtenerComunidadPorId(Long id) throws Exception {
    String sql = "SELECT * FROM Comunidad WHERE id_comunidad = ?";
    try (Connection conexion = DatabaseManager.getConnection();
         PreparedStatement stmt = conexion.prepareStatement(sql)) {

        stmt.setLong(1, id); // Asigna el ID al parámetro de la consulta
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Comunidad comunidad = new Comunidad();
            comunidad.setId_comunidad(rs.getLong("id_comunidad"));
            comunidad.setNombre(rs.getString("nombre"));
            comunidad.setDescripcion(rs.getString("descripcion"));
            comunidad.setCategoria(rs.getString("categoria"));
            comunidad.setFechaCreacion(rs.getDate("fechaCreacion")); // Ajusta al formato de la fecha
            return comunidad;
        } else {
            throw new Exception("No se encontró la comunidad con ID: " + id);
        }
    } catch (SQLException e) {
        throw new Exception("Error al obtener la comunidad: " + e.getMessage(), e);
    }
}



        /*
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
*/


}
