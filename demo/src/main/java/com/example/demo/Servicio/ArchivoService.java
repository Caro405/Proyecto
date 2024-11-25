package com.example.demo.Servicio;

import com.example.demo.Entidades.Archivo;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArchivoService {

    private static final String UPLOAD_DIR = "uploads/";

    public ArchivoService() {
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
    }

    public void subirArchivo(String nombreArchivo, byte[] contenido) throws IOException {
        Path path = Paths.get(UPLOAD_DIR + nombreArchivo);
        Files.write(path, contenido);
    }

    public Path descargarArchivo(String nombreArchivo) throws IOException {
        Path path = Paths.get(UPLOAD_DIR + nombreArchivo);
        if (!Files.exists(path)) {
            throw new IOException("El archivo no existe: " + nombreArchivo);
        }
        return path;
    }

    public List<Archivo> listarArchivos() {
        List<Archivo> archivos = new ArrayList<>();
        File folder = new File(UPLOAD_DIR);
        File[] files = folder.listFiles();
    
        if (files != null) {
            for (File file : files) {
                Archivo archivo = new Archivo(
                        file.getName(),
                        file.length(),
                        new Date(file.lastModified())
                );
                archivos.add(archivo);
                System.out.println("Archivo encontrado: " + archivo.getNombreArchivo());
            }
        } else {
            System.out.println("No se encontraron archivos en el directorio.");
        }
        return archivos;
    }
    
}

