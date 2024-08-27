package com.co.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @OneToOne
    @JoinColumn(name = "horario_id")
    private Horario horario;

    @ElementCollection
    @CollectionTable(name = "estaciones", joinColumns = @JoinColumn(name = "ruta_id"))
    @Column(name = "estacion")
    private List<String> estaciones = new ArrayList<>();

    @OneToMany(mappedBy = "ruta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Asignacion> asignacions = new ArrayList<>();

    public <T> Ruta(Horario horario1, List<T> list) {
    }
}
