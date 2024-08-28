package com.co.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "conductor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Conductor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "cedula", unique = true, nullable = false)
    private String cedula;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "direccion")
    private String direccion;

    // Eliminamos @ManyToMany a buses
    // Añadimos la relación con Asignacion

    @OneToMany(mappedBy = "conductor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Asignacion> asignaciones = new HashSet<>();
}
