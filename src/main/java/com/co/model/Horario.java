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
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hora_inicio")
    private String horaInicio;

    @Column(name = "hora_fin")
    private String horaFin;

    @ElementCollection
    @CollectionTable(name = "dias", joinColumns = @JoinColumn(name = "horario_id"))
    @Column(name = "dia")
    private List<String> dias = new ArrayList<>();

    @OneToMany(mappedBy = "horario")
    private List<Sistema> sistemas; // Relación con Sistema
}
