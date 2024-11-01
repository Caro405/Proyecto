package com.example.demo.Archivo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchivoService {

    @Autowired
    private ArchivoRepository archivoRepository;

    public List<Archivo> obtenerTodosLosArchivos() {
        return archivoRepository.findAll();
    }

    public Archivo obtenerArchivoPorId(Long id) {
        return archivoRepository.findById(id).orElse(null);
    }

    public Archivo guardarArchivo(Archivo archivo) {
        return archivoRepository.save(archivo);
    }

    public void eliminarArchivo(Long id) {
        archivoRepository.deleteById(id);
    }
}
