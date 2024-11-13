package com.example.demo.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.Servicio.CuentaServicio;

@Controller
public class RegistroController {

    private final CuentaServicio cuentaServicio;

    public RegistroController(CuentaServicio cuentaServicio) {
        this.cuentaServicio = cuentaServicio;
    }

    @GetMapping("/Registrar")
    public String mostrarRegistrar() {
        return "Registrar";
    }

    @PostMapping("/Registrar")
    public String registroUsuario(
            @RequestParam String correo,
            @RequestParam String nombre,
            @RequestParam String contrasena,
            @RequestParam String rol,
            RedirectAttributes redirectAttributes) {
        try {
            String rMensaje = cuentaServicio.crearUsuario(correo, nombre, rol, contrasena);
            System.out.println(rMensaje);

            redirectAttributes.addFlashAttribute("success", rMensaje);

            return "redirect:/Login";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();

            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/Registrar";
        }
    }
}

