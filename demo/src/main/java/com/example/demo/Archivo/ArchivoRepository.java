package com.example.demo.Archivo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchivoRepository extends JpaRepository<Archivo, Long> {
    // Aquí puedes agregar consultas personalizadas si es necesario
}

