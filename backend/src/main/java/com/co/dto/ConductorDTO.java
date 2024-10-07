package com.co.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class ConductorDTO {
    private long id;
    private String nombre;
    private String cedula;
    private String telefono;
    private String direccion;
}
