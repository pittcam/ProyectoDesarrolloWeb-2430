package com.co.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class BusDTO {
    private Long id;
    private Long ruta_id;
    private String numeroPlaca;
    private String modelo;

    public BusDTO(Long id, String numeroPlaca, String modelo) {
    }
}
