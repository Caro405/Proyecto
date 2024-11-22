package com.example.demo.Servicio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.DatabaseManager.DatabaseManager;
import com.example.demo.Entidades.*;

@Service
public class PublicacionService {


public List<Comentario> obtenerComentariosPorPublicacion(Long idPublicacion) throws Exception {
    List<Comentario> comentarios = new ArrayList<>();
    String sql = "SELECT * FROM Comentario WHERE id_publicacion = ?";

    try (Connection conexion = DatabaseManager.getConnection();
         PreparedStatement stmt = conexion.prepareStatement(sql)) {

        stmt.setLong(1, idPublicacion);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Comentario comentario = new Comentario();
            comentario.setId(rs.getLong("id"));
            comentario.setContenido(rs.getString("contenido"));
            comentario.setAutor(rs.getString("autor"));
            comentarios.add(comentario);
        }
    } catch (SQLException e) {
        throw new Exception("Error al obtener comentarios: " + e.getMessage(), e);
    }

    return comentarios;
}



public List<Publicacion> obtenerPublicacionesPorComunidad(Long idComunidad) throws Exception {
    List<Publicacion> publicaciones = new ArrayList<>();
    String sql = "SELECT * FROM Publicacion WHERE id = ?";

    try (Connection conexion = DatabaseManager.getConnection();
         PreparedStatement stmt = conexion.prepareStatement(sql)) {

        stmt.setLong(1, id_publicacion);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Publicacion publicacion = new Publicacion();
            publicacion.setId_publicacion(rs.getLong("id"));
            publicacion.setTitulo(rs.getString("titulo"));
            publicacion.setDescripcion(rs.getString("descripcion"));
            publicaciones.add(publicacion);
        }
    } catch (SQLException e) {
        throw new Exception("Error al obtener publicaciones: " + e.getMessage(), e);
    }
    return publicaciones;
}



}
