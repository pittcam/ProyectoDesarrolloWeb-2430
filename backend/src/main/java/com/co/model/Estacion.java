package com.co.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "estacion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Estacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @ManyToMany
    @JoinTable(
            name = "ruta_estacion",
            joinColumns = @JoinColumn(name = "estacion_id"),
            inverseJoinColumns = @JoinColumn(name = "ruta_id")
    )
    private Set<Ruta> rutas = new HashSet<>();

}
