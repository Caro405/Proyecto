package com.example.demo.Controladores;

import org.springframework.stereotype.Controller;
//import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.example.demo.Servicio.ComunidadService;


import java.lang.ProcessBuilder.Redirect;
import java.util.Date;

//import java.util.List;


@Controller
@RequestMapping("/PantallaInicio")
public class ComunidadController {

    //Para crear comunidad
    private ComunidadService comunidadService;

    public void ComunidadController (ComunidadService comunidadService) {
            this.comunidadService = comunidadService;
    }

    @GetMapping("/CrearComunidad")
    public String mostrarCrearComunidad() {
        return "CrearComunidad";
    }

    @PostMapping("/CrearComunidad")
    public String crearComunidad (@RequestParam String nombre,
            @RequestParam String descripcion,
            @RequestParam Date fechaCreacion,
            @RequestParam String categoria,
            
            RedirectAttributes redirectAttributes){
        try{
                String rMensaje = comunidadService.crearComunidad(nombre, descripcion, fechaCreacion, categoria);
                System.out.println(rMensaje);
    
                redirectAttributes.addFlashAttribute("success", rMensaje);
    
                return "redirect:/Comunidad";

            }catch (Exception e){
                System.out.println(e.getMessage());
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/CrearComunidad";
            }
        }


/*

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
