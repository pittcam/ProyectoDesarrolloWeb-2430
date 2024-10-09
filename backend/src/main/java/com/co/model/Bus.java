package com.co.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bus")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_placa", unique = true, nullable = false)
    private String numeroPlaca;

    @Column(name = "modelo", nullable = false)
    private String modelo;

    @ManyToOne
    @JoinColumn(name = "ruta_id")
    private Ruta ruta;


    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Asignacion> asignaciones = new HashSet<>();
}
