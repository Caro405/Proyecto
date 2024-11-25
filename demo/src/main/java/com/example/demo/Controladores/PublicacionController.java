package com.example.demo.Controladores;

import com.example.demo.Entidades.*;
import com.example.demo.Servicio.ComunidadService;
import com.example.demo.Servicio.PublicacionService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Publicacion")
public class PublicacionController {

    private final ComunidadService comunidadService;
    private final PublicacionService publicacionService;

    public PublicacionController(ComunidadService comunidadService, PublicacionService publicacionService) {
        this.comunidadService = comunidadService;
        this.publicacionService = publicacionService;
    }

    // Mostrar el formulario para una nueva publicación
    @GetMapping("/NuevaPublicacion/{idComunidad}")
    public String mostrarNuevaPublicacion(@PathVariable Long idComunidad, Model model) {
        System.out.println("Mostrando formulario para la comunidad con ID: " + idComunidad);
        model.addAttribute("idComunidad", idComunidad);
        return "NuevaPublicacion";
    }
    

    // Crear una nueva publicación
    @PostMapping("/Crear")
@ResponseBody // Devuelve datos en lugar de una vista
public Publicacion crearPublicacion(@RequestBody Publicacion publicacion) {
    try {
        // Guarda la publicación asociándola a la comunidad
        Publicacion nuevaPublicacion = publicacionService.crearPublicacion(publicacion.getIdComunidad(), publicacion);
        return nuevaPublicacion; // Devuelve la publicación creada
    } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("Error al crear la publicación: " + e.getMessage());
    }
}


}