package com.co.dto;

import com.co.model.Asignacion;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class RutaDTO {
    private Long id;
    private String nombre;
    private List<Asignacion> asignaciones;
}
