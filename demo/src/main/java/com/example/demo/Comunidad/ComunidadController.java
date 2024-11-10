package com.example.demo.Comunidad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entidades.Archivo;
import com.example.demo.Entidades.Comunidad;
import com.example.demo.Entidades.Usuario;

import java.util.List;

@RestController
@RequestMapping("/comunidades")
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

    // Métodos adicionales para los métodos específicos del diagrama
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
