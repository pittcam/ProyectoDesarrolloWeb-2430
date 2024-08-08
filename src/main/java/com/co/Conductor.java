package com.co;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Conductor {
    private Integer idConductor;
    private String cedula;
    private String nombre;
    private String telefono;
    private String direccion;
    private  List <BusAsignado> busesAsignados = new ArrayList<>();


}