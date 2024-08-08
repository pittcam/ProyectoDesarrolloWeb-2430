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

public class Ruta {
    private Integer id;
    private Horario horario;
    private List <String> estaciones = new ArrayList<>();


}