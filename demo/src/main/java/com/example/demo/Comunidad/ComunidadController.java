package com.example.demo.Comunidad;

import com.example.demo.Archivo.Archivo;
import com.example.demo.Usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comunidad")
public class ComunidadController {

    @Autowired
    private ComunidadService comunidadService;

    @GetMapping
    public List<Comunidad> obtenerComunidades() {
        return comunidadService.obtenerTodas();
    }

    @PostMapping
    public Comunidad crearComunidad(@RequestBody Comunidad comunidad) {
        return comunidadService.crearComunidad(comunidad);
    }

    @DeleteMapping("/{id}")
    public void eliminarComunidad(@PathVariable Long id) {
        comunidadService.eliminarComunidad(id);
    }

    @PostMapping("/{id}/agregarUsuario")
    public void agregarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        comunidadService.agregarUsuario(id, usuario);
    }

    @DeleteMapping("/{id}/eliminarUsuario")
    public void eliminarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        comunidadService.eliminarUsuario(id, usuario);
    }

    @PostMapping("/{id}/publicarArchivo")
    public void publicarArchivo(@PathVariable Long id, @RequestBody Archivo archivo) {
        comunidadService.publicarArchivo(id, archivo);
    }
}

