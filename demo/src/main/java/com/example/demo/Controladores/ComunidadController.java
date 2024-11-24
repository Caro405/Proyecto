package com.example.demo.Controladores;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Servicio.*;
import com.example.demo.Entidades.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/PantallaInicio")
public class ComunidadController {

    private final ComunidadService comunidadService;
    private final PublicacionService publicacionService;

    // Constructor con inyección de servicios
    public ComunidadController(ComunidadService comunidadService, PublicacionService publicacionService) {
        this.comunidadService = comunidadService;
        this.publicacionService = publicacionService;
    }


        // Mostrar la pantalla para crear una comunidad
        @GetMapping("/CrearComunidad")
        public String mostrarCrearComunidad() {
                return "CrearComunidad";
            }
        
    
        // Crear una nueva comunidad
        @PostMapping("/CrearComunidad")
        public String crearComunidad(
                @RequestParam String nombre,
                @RequestParam String descripcion,
                @RequestParam String categoria,
                Model model) {
            try {
                // Crear un objeto Comunidad
                Comunidad comunidad = new Comunidad();
                comunidad.setNombre(nombre);
                comunidad.setDescripcion(descripcion);
                comunidad.setCategoria(categoria);
                comunidad.setFechaCreacion(new Date()); // Fecha actual
    
                // Guardar la comunidad utilizando el servicio
                Comunidad nuevaComunidad = comunidadService.crearComunidad(comunidad);
    
                // Agregar la comunidad al modelo
                model.addAttribute("comunidad", nuevaComunidad);
    
                return "Comunidad"; // Renderizar la vista Comunidad.html
            } catch (Exception e) {
                // Manejo de errores
                model.addAttribute("error", e.getMessage());
                return "CrearComunidad";
            }
        }
  /* 

        // Mostrar la pantalla para explorar comunidades
    @GetMapping("/ExplorarComunidades")
    public String explorarComunidades(Model model) {
        //model.addAttribute("comunidades", comunidadService.obtenerTodas());
        return "ExplorarComunidades";
    }

*/
    // Mostrar la pantalla para el banco de archivos
    @GetMapping("/BancoDeArchivos")
    public String mostrarBancoDeArchivos() {
        return "BancoDeArchivos";
    }

    // Mostrar la pantalla para crear una nueva publicación
    @GetMapping("/NuevaPublicacion")
    public String mostrarNuevaPublicacion() {
        return "NuevaPublicacion";
    }

    //Publicaciones
    @GetMapping("/Publicaciones/{idComunidad}")
    public String listarPublicaciones(@PathVariable Long idComunidad, Model model) {
    try {
        Comunidad comunidad = comunidadService.obtenerComunidadPorId(idComunidad);
        List<Publicacion> publicaciones = publicacionService.obtenerPublicacionesPorComunidad(idComunidad);

        model.addAttribute("comunidad", comunidad);
        model.addAttribute("publicaciones", publicaciones);

        return "Publicaciones";
    } catch (Exception e) {
        model.addAttribute("error", e.getMessage());
        return "Error";
    }
}


    // Ver detalles de una comunidad (incluye publicaciones y comentarios)
    @GetMapping("/PantallaInicio/Comunidad/{idComunidad}")
    public String verDetallesComunidad(@PathVariable Long idComunidad, Model model) {
        try {
            Comunidad comunidad = comunidadService.obtenerComunidadPorId(idComunidad);
            List<Publicacion> publicaciones = publicacionService.obtenerPublicacionesPorComunidad(idComunidad);
            comunidad.setPublicaciones(publicaciones);
    
            model.addAttribute("comunidad", comunidad);
            return "Comunidad"; // Nombre del archivo HTML para los detalles de la comunidad
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "Error"; // Vista para manejar errores
        }
    }
    
    @GetMapping("/Publicacion/{idComunidad}")
public String nuevaPublicacion(@PathVariable Long idComunidad, Model model) {
    model.addAttribute("idComunidad", idComunidad);
    return "NuevaPublicacion"; 
}




  



}