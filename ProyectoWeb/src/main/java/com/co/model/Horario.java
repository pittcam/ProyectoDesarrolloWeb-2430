package com.co.model;

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

public class Horario {
    private Integer id;
    private String horaInicio;
    private String horaFin;
    private List<String> dias = new ArrayList<>();

}