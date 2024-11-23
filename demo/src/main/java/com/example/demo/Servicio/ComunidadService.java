package com.example.demo.Servicio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.DatabaseManager.DatabaseManager;
import com.example.demo.Entidades.Comunidad;

@Service
public class ComunidadService {

    // Crear comunidad
    public Comunidad crearComunidad(Comunidad comunidad) throws Exception {
        String sql = "INSERT INTO Comunidad (nombre, descripcion, fechaCreacion, categoria) VALUES (?, ?, ?, ?)";

        try (Connection conexion = DatabaseManager.getConnection();
             PreparedStatement stmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, comunidad.getNombre());
            stmt.setString(2, comunidad.getDescripcion());
            stmt.setDate(3, new java.sql.Date(comunidad.getFechaCreacion().getTime()));
            stmt.setString(4, comunidad.getCategoria());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new Exception("No se pudo crear la comunidad.");
            }

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                comunidad.setId_comunidad(generatedKeys.getLong(1));
            }

            return comunidad;
        }
    }

    // Obtener todas las comunidades
    public List<Comunidad> obtenerTodas() throws Exception {
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

            return comunidades;
        }
    }

    // Obtener una comunidad por ID
    public Comunidad obtenerComunidadPorId(Long id) throws Exception {
        String sql = "SELECT * FROM Comunidad WHERE id_comunidad = ?";
        try (Connection conexion = DatabaseManager.getConnection();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Comunidad comunidad = new Comunidad();
                comunidad.setId_comunidad(rs.getLong("id_comunidad"));
                comunidad.setNombre(rs.getString("nombre"));
                comunidad.setDescripcion(rs.getString("descripcion"));
                comunidad.setFechaCreacion(rs.getDate("fechaCreacion"));
                comunidad.setCategoria(rs.getString("categoria"));
                return comunidad;
            } else {
                throw new Exception("No se encontró la comunidad con ID: " + id);
            }
        }
    }

    /* 
    // Actualizar una comunidad
    public void actualizarComunidad(Comunidad comunidad) throws Exception {
        String sql = "UPDATE Comunidad SET nombre = ?, descripcion = ?, categoria = ? WHERE id_comunidad = ?";

        try (Connection conexion = DatabaseManager.getConnection();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setString(1, comunidad.getNombre());
            stmt.setString(2, comunidad.getDescripcion());
            stmt.setString(3, comunidad.getCategoria());
            stmt.setLong(4, comunidad.getId_comunidad());

            stmt.executeUpdate();
        }
    }

    // Eliminar una comunidad
    public void eliminarComunidad(Long id) throws Exception {
        String sql = "DELETE FROM Comunidad WHERE id_comunidad = ?";

        try (Connection conexion = DatabaseManager.getConnection();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
        */
}