package com.co;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Conductor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cedula;
    private String nombre;
    private String telefono;
    private String direccion;

    @ManyToMany
    @JoinTable(
            name = "Bus_Asignado",
            joinColumns = @JoinColumn(name = "idConductor"),
            inverseJoinColumns = @JoinColumn(name = "idBus")
    )
    private Set<Bus> busesAsignados = new HashSet<>();

}