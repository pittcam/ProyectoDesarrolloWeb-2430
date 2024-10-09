package com.co.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ruta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "ruta", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Asignacion> asignaciones = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "ruta_estacion",
            joinColumns = @JoinColumn(name = "ruta_id"),
            inverseJoinColumns = @JoinColumn(name = "estacion_id")
    )
    private Set<Estacion> estaciones = new HashSet<>();
}
