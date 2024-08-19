package com.co.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    @OneToMany(mappedBy = "conductor")
    private List<Sistema> idConductores;





}