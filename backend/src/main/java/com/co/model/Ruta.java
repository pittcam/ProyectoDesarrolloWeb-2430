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

    // Relación de muchos a muchos con estaciones
    @ManyToMany
    @JoinTable(
            name = "ruta_estacion",
            joinColumns = @JoinColumn(name = "ruta_id"),
            inverseJoinColumns = @JoinColumn(name = "estacion_id")
    )
    private Set<Estacion> estaciones = new HashSet<>();

    // IDs de los horarios de funcionamiento
    @ElementCollection
    @CollectionTable(name = "ruta_horario", joinColumns = @JoinColumn(name = "ruta_id"))
    @Column(name = "horario_id") // Almacena los IDs de los horarios
    private Set<Long> horarioFuncionamiento = new HashSet<>();

    // Relación con asignaciones de buses
    @OneToMany(mappedBy = "ruta", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Asignacion> asignaciones = new HashSet<>();
}
