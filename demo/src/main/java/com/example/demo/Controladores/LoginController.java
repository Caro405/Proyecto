package com.example.demo.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.Servicio.CuentaServicio;

@Controller
public class LoginController {
    private final CuentaServicio cuentaServicio;

    public LoginController(CuentaServicio cuentaServicio) {
        this.cuentaServicio = cuentaServicio;
    }

    @GetMapping("/")
    public String redirigirLogin() {
        return "redirect:/Login";
    }
    @GetMapping("/PantallaInicio")
public String mostrarComunidadA() {
    return "PantallaInicio";
}


    @GetMapping("/Login")
    public String showLoginPage(@RequestParam(required = false) String param) {
        return "Login";
    }

    @PostMapping("/Login")
    public String login(@RequestParam String correo,
                        @RequestParam String contrasena,
                        RedirectAttributes redirectAttributes) {
        boolean okLoginp = cuentaServicio.login(correo, contrasena);
        if (okLoginp) {
            return "redirect:/ComunidadA";
        } else {
            redirectAttributes.addFlashAttribute("error", "usuario o contraseña incorrectos");
            return "redirect:/Login";
        }
    }
}
