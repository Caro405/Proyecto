package com.example.demo.Servicio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Entidades.Comunidad;

import javax.sql.DataSource;

@Service
public class ComunidadService {

    private final DataSource dataSource;

    // Constructor con inyección de DataSource
    public ComunidadService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    // Crear comunidad
    public Comunidad crearComunidad(Comunidad comunidad) throws SQLException {
        String sql = "INSERT INTO Comunidad (nombre, descripcion, fechaCreacion, categoria) VALUES (?, ?, ?, ?)";

        try (Connection conexion = getConnection();
             PreparedStatement stmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, comunidad.getNombre());
            stmt.setString(2, comunidad.getDescripcion());
            stmt.setDate(3, new java.sql.Date(comunidad.getFechaCreacion().getTime()));
            stmt.setString(4, comunidad.getCategoria());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No se pudo crear la comunidad.");
            }

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                comunidad.setId_comunidad(generatedKeys.getLong(1));
            }

            return comunidad;
        }
    }

    // Obtener todas las comunidades
    public List<Comunidad> obtenerTodas() throws SQLException {
        String sql = "SELECT * FROM Comunidad";
        List<Comunidad> comunidades = new ArrayList<>();

        try (Connection conexion = getConnection();
             Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                comunidades.add(mapearComunidad(rs));
            }

            return comunidades;
        }
    }

    // Obtener una comunidad por ID
    public Comunidad obtenerComunidadPorId(Long id) throws SQLException {
        String sql = "SELECT * FROM Comunidad WHERE id_comunidad = ?";

        try (Connection conexion = getConnection();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearComunidad(rs);
                } else {
                    throw new SQLException("No se encontró la comunidad con ID: " + id);
                }
            }
        }
    }

    // Mapper para convertir ResultSet en Comunidad
    private Comunidad mapearComunidad(ResultSet rs) throws SQLException {
        Comunidad comunidad = new Comunidad();
        comunidad.setId_comunidad(rs.getLong("id_comunidad"));
        comunidad.setNombre(rs.getString("nombre"));
        comunidad.setDescripcion(rs.getString("descripcion"));
        comunidad.setFechaCreacion(rs.getDate("fechaCreacion"));
        comunidad.setCategoria(rs.getString("categoria"));
        return comunidad;
    }
}
