package com.example.demo.Usuario;


import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository <Usuario, Long>{
    Usuario findByUsername (String correo);
}
