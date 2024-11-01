package com.example.demo.Archivo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/archivos")
public class ArchivoController {

    @Autowired
    private ArchivoService archivoService;

    @GetMapping
    public List<Archivo> obtenerTodosLosArchivos() {
        return archivoService.obtenerTodosLosArchivos();
    }

    @GetMapping("/{id}")
    public Archivo obtenerArchivoPorId(@PathVariable Long id) {
        return archivoService.obtenerArchivoPorId(id);
    }

    @PostMapping
    public Archivo guardarArchivo(@RequestBody Archivo archivo) {
        return archivoService.guardarArchivo(archivo);
    }

    @DeleteMapping("/{id}")
    public void eliminarArchivo(@PathVariable Long id) {
        archivoService.eliminarArchivo(id);
    }
}

