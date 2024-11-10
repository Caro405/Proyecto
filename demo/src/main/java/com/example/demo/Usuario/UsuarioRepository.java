package com.example.demo.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByCorreo(String correo);
}
