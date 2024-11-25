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

    // Constructor
    public ArchivoController(ArchivoService archivoService) {
        this.archivoService = archivoService;
    }

    // Mostrar el banco de archivos
    @GetMapping
    public String mostrarBancoDeArchivos(Model model) {
        // Llama al servicio para listar los archivos y añade al modelo
        List<Archivo> archivos = archivoService.listarArchivos();
        model.addAttribute("archivos", archivos); // Añade los archivos al modelo para que se muestren en la vista
        return "BancoDeArchivos"; // Retorna la vista del banco de archivos
    }

    // Subir un archivo
    @PostMapping("/subir")
    public String subirArchivo(@RequestParam("archivo") MultipartFile archivo, RedirectAttributes redirectAttributes) {
        try {
            archivoService.subirArchivo(archivo.getOriginalFilename(), archivo.getBytes());
            redirectAttributes.addFlashAttribute("success", "Archivo subido exitosamente.");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "Error al subir el archivo.");
            e.printStackTrace();
        }
        return "redirect:/BancoDeArchivos"; // Redirige al banco de archivos después de subir
    }

    // Descargar un archivo
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
