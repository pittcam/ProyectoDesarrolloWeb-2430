package com.co.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "horario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hora_inicio", nullable = false)
    private String horaInicio;

    @Column(name = "hora_fin", nullable = false)
    private String horaFin;

    // Eliminamos @ManyToMany a buses
    // Añadimos la relación con Asignacion

    @OneToMany(mappedBy = "horario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Asignacion> asignaciones = new HashSet<>();
}
