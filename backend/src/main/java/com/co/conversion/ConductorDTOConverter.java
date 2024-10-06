package com.co.conversion;

import org.springframework.stereotype.Component;

import com.co.dto.ConductorDTO;
import com.co.model.Conductor;

import java.util.HashSet;

@Component
public class ConductorDTOConverter {

    // Convertir de entidad a DTO
    public ConductorDTO entityToDTO(Conductor conductor) {
        return new ConductorDTO(
                conductor.getId(),
                conductor.getNombre(),
                conductor.getCedula(),
                conductor.getTelefono(),
                conductor.getDireccion()
        );
    }

    // Convertir de DTO a entidad
    public Conductor DTOToEntity(ConductorDTO conductorDTO) {
        return new Conductor(
                conductorDTO.getId(),
                conductorDTO.getNombre(),
                conductorDTO.getCedula(),
                conductorDTO.getTelefono(),
                conductorDTO.getDireccion(),
                new HashSet<>() // Asignaciones se inicializa como un conjunto vacío
        );
    }
}
