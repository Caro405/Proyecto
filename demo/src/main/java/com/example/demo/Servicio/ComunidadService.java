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

            // Manejo de la fecha, con validación de null
            if (comunidad.getFechaCreacion() != null) {
                stmt.setDate(3, java.sql.Date.valueOf(comunidad.getFechaCreacion()));
            } else {
                stmt.setDate(3, null);
            }

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
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al crear la comunidad: " + e.getMessage());
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
                comunidad.setFechaCreacion(rs.getDate("fechaCreacion").toLocalDate());
                comunidad.setCategoria(rs.getString("categoria"));

                comunidades.add(comunidad);
            }

            return comunidades;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener todas las comunidades: " + e.getMessage());
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
                comunidad.setFechaCreacion(rs.getDate("fechaCreacion").toLocalDate());
                comunidad.setCategoria(rs.getString("categoria"));
                return comunidad;
            } else {
                throw new Exception("No se encontró la comunidad con ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la comunidad por ID: " + e.getMessage());
        }
    }

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
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar la comunidad: " + e.getMessage());
        }
    }

    // Eliminar una comunidad
    public void eliminarComunidad(Long id) throws Exception {
        String sql = "DELETE FROM Comunidad WHERE id_comunidad = ?";

        try (Connection conexion = DatabaseManager.getConnection();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al eliminar la comunidad: " + e.getMessage());
        }
    }
}
