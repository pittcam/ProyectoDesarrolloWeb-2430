package com.co;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bus {
    private Integer idBus;
    private String placa;
    private String modelo;
    private List<RutaAsignada> rutasAsignadas = new ArrayList<>();

    @ManyToMany(mappedBy = "busesAsignados")
    private Set<Conductor> conductorAsignado = new HashSet<>();


}