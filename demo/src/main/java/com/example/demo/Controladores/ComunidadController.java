package com.example.demo.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.demo.Servicio.ComunidadService;

@Controller
@RequestMapping("/PantallaInicio")
public class ComunidadController {

    private final ComunidadService comunidadService;

    // Constructor
    public ComunidadController(ComunidadService comunidadService) {
        this.comunidadService = comunidadService;
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
}
