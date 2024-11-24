package com.example.demo.Entidades;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Publicacion") // Nombre de la tabla en la base de datos
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_publicacion")
    private Long id_publicacion;

    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_comunidad", referencedColumnName = "id_comunidad") // Relación con Comunidad
    private Comunidad comunidad;

    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL, orphanRemoval = true) // Relación con Comentario
    private List<Comentario> comentarios;
}
