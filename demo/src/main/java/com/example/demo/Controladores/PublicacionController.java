package com.example.demo.Controladores;

import com.example.demo.Entidades.*;
import com.example.demo.Servicio.PublicacionService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Publicacion")
public class PublicacionController {

    private final PublicacionService publicacionService;

    public PublicacionController(PublicacionService publicacionService) {
        this.publicacionService = publicacionService;
    }

    // Mostrar el formulario para una nueva publicación
    @GetMapping("/NuevaPublicacion/{idComunidad}")
    public String mostrarNuevaPublicacion(@PathVariable Long idComunidad, Model model) {
        model.addAttribute("idComunidad", idComunidad);
        return "NuevaPublicacion";
    }

    // Crear una nueva publicación
    @PostMapping("/Crear")
    public String crearPublicacion(
            @RequestParam Long idComunidad,
            @RequestParam String titulo,
            @RequestParam String descripcion,
            Model model) {
        try {
            Publicacion publicacion = new Publicacion();
            publicacion.setTitulo(titulo);
            publicacion.setDescripcion(descripcion);

            publicacionService.crearPublicacion(idComunidad, publicacion);
            return "redirect:/PantallaInicio/Publicaciones/" + idComunidad; // Actualiza la redirección a la ruta correcta
        } catch (Exception e) {
            model.addAttribute("error", "Error al crear la publicación: " + e.getMessage());
            return "NuevaPublicacion";
        }
    }
}
