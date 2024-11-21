package com.example.demo.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/BancoDeArchivos")
public class BancoDeArchivosController {

    // Ruta donde se guardarán los archivos
    private static final String UPLOAD_DIR = "uploads/";

    // Constructor para inicializar el directorio de subida
    public BancoDeArchivosController() {
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
    }

    // Mostrar el Banco de Archivos
    @GetMapping
    public String mostrarBancoDeArchivos(Model model) {
        // Obtener la lista de archivos en el directorio
        File folder = new File(UPLOAD_DIR);
        File[] files = folder.listFiles();

        List<String> archivos = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                archivos.add(file.getName());
            }
        }

        model.addAttribute("archivos", archivos);
        return "BancoDeArchivos";
    }

    // Subir un archivo
    @PostMapping("/subir")
    public String subirArchivo(@RequestParam("archivo") MultipartFile archivo, RedirectAttributes redirectAttributes) {
        if (archivo.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Seleccione un archivo para subir.");
            return "redirect:/BancoDeArchivos";
        }

        try {
            // Guardar el archivo en el directorio
            byte[] bytes = archivo.getBytes();
            Path path = Paths.get(UPLOAD_DIR + archivo.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("success", "Archivo subido exitosamente: " + archivo.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error al subir el archivo.");
        }

        return "redirect:/BancoDeArchivos";
    }
}
