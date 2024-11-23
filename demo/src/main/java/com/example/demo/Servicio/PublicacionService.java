package com.example.demo.Servicio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.DatabaseManager.DatabaseManager;
import com.example.demo.Entidades.*;

@Service
public class PublicacionService {

    public Publicacion crearPublicacion(Long idComunidad, Publicacion publicacion) throws Exception {
        String sql = "INSERT INTO Publicacion (titulo, descripcion, id_comunidad) VALUES (?, ?, ?)";

        try (Connection conexion = DatabaseManager.getConnection();
             PreparedStatement stmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, publicacion.getTitulo());
            stmt.setString(2, publicacion.getDescripcion());
            stmt.setLong(3, idComunidad);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new Exception("No se pudo crear la publicación.");
            }

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                publicacion.setId_publicacion(generatedKeys.getLong(1));
            }

            return publicacion;
        } catch (SQLException e) {
            throw new Exception("Error al crear la publicación: " + e.getMessage(), e);
        }
    }

    public Publicacion obtenerPublicacionPorId(Long id) throws Exception {
        String sql = "SELECT * FROM Publicacion WHERE id_publicacion = ?";

        try (Connection conexion = DatabaseManager.getConnection();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Publicacion publicacion = new Publicacion();
                publicacion.setId(rs.getLong("id_publicacion"));
                publicacion.setTitulo(rs.getString("titulo"));
                publicacion.setDescripcion(rs.getString("descripcion"));
                return publicacion;
            } else {
                throw new Exception("No se encontró la publicación con ID: " + id);
            }
        } catch (SQLException e) {
            throw new Exception("Error al obtener la publicación: " + e.getMessage(), e);
        }
    }

    //publicaciones por comunidad
    public List<Publicacion> obtenerPublicacionesPorComunidad(Long idComunidad) throws Exception {
        List<Publicacion> publicaciones = new ArrayList<>();
        String sql = "SELECT * FROM Publicacion WHERE id_comunidad = ?";
    
        try (Connection conexion = DatabaseManager.getConnection();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {
    
            stmt.setLong(1, idComunidad); // Pasa el ID de la comunidad
            ResultSet rs = stmt.executeQuery();
    
            while (rs.next()) {
                Publicacion publicacion = new Publicacion();
                publicacion.setId(rs.getLong("id_publicacion"));
                publicacion.setTitulo(rs.getString("titulo"));
                publicacion.setDescripcion(rs.getString("descripcion"));
                publicaciones.add(publicacion);
            }
        } catch (SQLException e) {
            throw new Exception("Error al obtener publicaciones: " + e.getMessage(), e);
        }
        return publicaciones;
    }
    

    //comentarios por publicacion
    public List<Comentario> obtenerComentariosPorPublicacion(Long idPublicacion) throws Exception {
        List<Comentario> comentarios = new ArrayList<>();
        String sql = "SELECT * FROM Comentario WHERE id_publicacion = ?";
    
        try (Connection conexion = DatabaseManager.getConnection();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {
    
            stmt.setLong(1, idPublicacion); // Asegúrate de usar el campo correcto
            ResultSet rs = stmt.executeQuery();
    
            while (rs.next()) {
                Comentario comentario = new Comentario();
                comentario.setId(rs.getLong("id")); // Asegúrate de que el nombre del campo coincida con tu esquema
                comentario.setContenido(rs.getString("contenido"));
                comentario.setAutor(rs.getString("autor"));
                comentarios.add(comentario);
            }
        } catch (SQLException e) {
            throw new Exception("Error al obtener comentarios: " + e.getMessage(), e);
        }
    
        return comentarios;
    }

}