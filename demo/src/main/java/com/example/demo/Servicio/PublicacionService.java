package com.example.demo.Servicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.DatabaseManager.DatabaseManager;

import com.example.demo.Entidades.Publicacion;

@Service
public class PublicacionService {

    public Publicacion crearPublicacion(Long idComunidad, Publicacion publicacion) throws Exception {
        System.out.println("Datos recibidos:");
        System.out.println("Título: " + publicacion.getTitulo());
        System.out.println("Descripción: " + publicacion.getDescripcion());
        System.out.println("ID Comunidad: " + idComunidad);
    
        String sql = "INSERT INTO Publicacion (titulo, descripcion, id_comunidad) VALUES (?, ?, ?)";
    
        try (Connection conexion = DatabaseManager.getConnection();
             PreparedStatement stmt = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
    
            stmt.setString(1, publicacion.getTitulo());
            stmt.setString(2, publicacion.getDescripcion());
            stmt.setLong(3, idComunidad);
    
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No se pudo crear la publicación.");
            }
    
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                publicacion.setId_publicacion(generatedKeys.getLong(1));
            }
    
            return publicacion;
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
            throw e;
        }
    }
    

    public Publicacion obtenerPublicacionPorId(Long id) throws Exception {
        String sql = "SELECT * FROM Publicacion WHERE id_publicacion = ?";
        try (Connection conexion = DatabaseManager.getConnection();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearPublicacion(rs);
            } else {
                throw new Exception("No se encontró la publicación con ID: " + id);
            }
        }
    }

    private Publicacion mapearPublicacion(ResultSet rs) throws SQLException {
        Publicacion publicacion = new Publicacion();
        publicacion.setId_publicacion(rs.getLong("id_publicacion"));
        publicacion.setTitulo(rs.getString("titulo"));
        publicacion.setDescripcion(rs.getString("descripcion"));
        return publicacion;
    }

    public List<Publicacion> obtenerPublicacionesPorComunidad(Long idComunidad) throws Exception {
        String sql = "SELECT * FROM Publicacion WHERE id_comunidad = ?";
        List<Publicacion> publicaciones = new ArrayList<>();

        try (Connection conexion = DatabaseManager.getConnection();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setLong(1, idComunidad);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                publicaciones.add(mapearPublicacion(rs));
            }
        }

        return publicaciones;
    }

}
