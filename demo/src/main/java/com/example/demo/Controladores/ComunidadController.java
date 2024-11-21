package com.example.demo.Controladores;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.demo.Servicio.ComunidadService;
import com.example.demo.Entidades.*;
import org.springframework.ui.Model;


@Controller
@RequestMapping("/PantallaInicio")
public class ComunidadController {

    private final ComunidadService comunidadService;

    // Constructor
    public ComunidadController(ComunidadService comunidadService) {
        this.comunidadService = comunidadService;
    }

    // Mostrar la pantalla para explorar comunidades
    @GetMapping("/ExplorarComunidades")
    public String explorarComunidades(Model model) {
        model.addAttribute("comunidades", comunidadService.obtenerTodas());
        return "ExplorarComunidades";
    }
    
    // Mostrar la pantalla para crear una comunidad
    @GetMapping("/CrearComunidad")
    public String mostrarCrearComunidad() {
        return "CrearComunidad";
    }

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


    //
    //
    
    // Crear una nueva comunidad y mostrar los datos en la pantalla Comunidad.html
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

        // Llamar al servicio para guardar la comunidad
        Comunidad nuevaComunidad = comunidadService.crearComunidad(comunidad);

        // Agregar la comunidad al modelo para mostrar en la vista
        model.addAttribute("comunidad", nuevaComunidad);
        return "Comunidad"; // Redirige a la vista Comunidad.html

    } catch (Exception e) {
        // Manejo de errores
        model.addAttribute("error", e.getMessage());
        return "CrearComunidad";
    }
}


/*
    // Crear una nueva comunidad
    @PostMapping("/CrearComunidad")
    public String crearComunidad(
            @RequestParam String nombre,
            @RequestParam String descripcion,
            @RequestParam String categoria,
            RedirectAttributes redirectAttributes) {
        try {
            // Llama al servicio para crear la comunidad, la fecha se genera automáticamente
            String rMensaje = comunidadService.crearComunidad(nombre, descripcion, categoria);
            System.out.println(rMensaje);

            // Mensaje de éxito
            redirectAttributes.addFlashAttribute("success", rMensaje);
            return "redirect:/PantallaInicio";

        } catch (Exception e) {
            // Manejo de errores
            System.out.println(e.getMessage());
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/CrearComunidad";
        }
    }
*/

}
