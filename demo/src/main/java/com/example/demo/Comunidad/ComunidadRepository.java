package com.example.demo.Comunidad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entidades.Comunidad;

@Repository
public interface ComunidadRepository extends JpaRepository<Comunidad, Long> {
    // Aquí puedes definir métodos adicionales si es necesario
}
