package com.example.demo.Controladores;

import com.example.demo.Entidades.Archivo;
import com.example.demo.Servicio.ArchivoService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Controller
@RequestMapping("/BancoDeArchivos")
public class ArchivoController {

    private final ArchivoService archivoService;

    public ArchivoController(ArchivoService archivoService) {
        this.archivoService = archivoService;
    }

    @GetMapping
    public String mostrarBancoDeArchivos(Model model) {
        List<Archivo> archivos = archivoService.listarArchivos();
        model.addAttribute("archivos", archivos);
        return "BancoDeArchivos";
    }

    @PostMapping("/subir")
    public String subirArchivo(@RequestParam("archivo") MultipartFile archivo, RedirectAttributes redirectAttributes) {
        try {
            archivoService.subirArchivo(archivo.getOriginalFilename(), archivo.getBytes());
            redirectAttributes.addFlashAttribute("success", "Archivo subido exitosamente.");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "Error al subir el archivo.");
            e.printStackTrace();
        }
        return "redirect:/BancoDeArchivos";
    }

    @GetMapping("/descargar/{nombreArchivo}")
    public ResponseEntity<Resource> descargarArchivo(@PathVariable String nombreArchivo) {
        try {
            Path filePath = archivoService.descargarArchivo(nombreArchivo);
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                throw new IOException("El archivo no se pudo encontrar o no es legible.");
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}

