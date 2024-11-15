package com.example.demo.Controladores;

import org.springframework.stereotype.Controller;
//import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import com.example.demo.Entidades.Comunidad;

/*import com.example.demo.Entidades.Archivo;

import com.example.demo.Entidades.Usuario;
import com.example.demo.Servicio.ComunidadService;

import java.util.List;
*/

@Controller
@RequestMapping("/ComunidadA")
public class ComunidadController {

    private Comunidad comunidad;
    
        public void RegistroController(Comunidad comunidad) {
            this.comunidad = comunidad;
    }

    @GetMapping("/ComunidadA")
    public String mostrarComunidadA() {
        return "ComunidadA";
    }

/*
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
        */
}
