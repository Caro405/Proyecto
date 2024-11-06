package com.example.demo.Comunidad;

import com.example.demo.Usuario.Usuario; // Importación necesaria
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Archivo.Archivo;
import java.util.List;

@Service
public class ComunidadService {

    @Autowired
    private ComunidadRepository comunidadRepository;

    public List<Comunidad> obtenerTodas() {
        return comunidadRepository.findAll();
    }

    public Comunidad crearComunidad(Comunidad comunidad) {
        return comunidadRepository.save(comunidad);
    }

    public void eliminarComunidad(Long id) {
        comunidadRepository.deleteById(id);
    }

    // Lógica adicional según los métodos del diagrama
    public void agregarUsuario(Long comunidadId, Usuario usuario) {
        // Lógica para agregar usuario a la comunidad
    }

    public void eliminarUsuario(Long comunidadId, Usuario usuario) {
        // Lógica para eliminar usuario de la comunidad
    }

    public void publicarArchivo(Long comunidadId, Archivo archivo) {
        // Lógica para publicar un archivo en la comunidad
    }
}
