package com.co.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Sistema {
    private String idBus;
    private String idConductor;
    private String idHorario;
    private String idRuta;

    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idBus")
    private Bus bus;

    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idConductor")
    private Conductor conductor;

    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idRuta")
    private Ruta ruta;

    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idHorario")
    private Horario horario;

}
