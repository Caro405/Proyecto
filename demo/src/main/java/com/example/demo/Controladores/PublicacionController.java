package com.example.demo.Controladores;

import com.example.demo.Entidades.*;
import com.example.demo.Servicio.PublicacionService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Publicacion")
public class PublicacionController {

    private final PublicacionService publicacionService;

   // @Autowired
    public PublicacionController(PublicacionService publicacionService) {
        this.publicacionService = publicacionService;
    }

    // Mostrar formulario para crear una nueva publicación
    @GetMapping("/Nueva/{idComunidad}")
    public String mostrarFormularioPublicacion(@PathVariable Long idComunidad, Model model) {
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

            // Asignar a la comunidad correspondiente
            Publicacion nuevaPublicacion = publicacionService.crearPublicacion(idComunidad, publicacion);

            model.addAttribute("publicacion", nuevaPublicacion);
            return "redirect:/Comunidad/" + idComunidad; // Redirige a los detalles de la comunidad
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "NuevaPublicacion";
        }
    }

    // Listar todas las publicaciones de una comunidad
    @GetMapping("/Listar/{idComunidad}")
    public String listarPublicaciones(@PathVariable Long idComunidad, Model model) {
        try {
            List<Publicacion> publicaciones = publicacionService.obtenerPublicacionesPorComunidad(idComunidad);
            model.addAttribute("publicaciones", publicaciones);
            model.addAttribute("idComunidad", idComunidad);
            return "ListaPublicaciones";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "Error";
        }
    }

    // Ver detalles de una publicación
    @GetMapping("/Comunidad/{idPublicacion}")
    public String verDetallesPublicacion(@PathVariable Long idPublicacion, Model model) {
        try {
            Publicacion publicacion = publicacionService.obtenerPublicacionPorId(idPublicacion);
            model.addAttribute("publicacion", publicacion);
            return "Publicacion";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "Error";
        }
    }
}