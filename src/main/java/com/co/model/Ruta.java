package com.co.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
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
    private Horario horario; // Suponiendo que horario es una entidad

    @ElementCollection
    @CollectionTable(name = "estaciones", joinColumns = @JoinColumn(name = "ruta_id"))
    @Column(name = "estacion")
    private List<String> estaciones = new ArrayList<>();

    @OneToMany(mappedBy = "ruta") // Asegúrate de que la propiedad en Sistema se llame ruta
    private List<Sistema> sistemas;
}
