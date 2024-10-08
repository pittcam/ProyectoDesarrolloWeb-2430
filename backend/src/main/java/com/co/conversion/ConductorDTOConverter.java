package com.co.conversion;

import org.springframework.stereotype.Component;

import com.co.dto.ConductorDTO;
import com.co.model.Conductor;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConductorDTOConverter {

    // Metodo para convertir de Conductor a ConductorDTO
    public ConductorDTO entityToDTO(Conductor conductor) {
        return new ConductorDTO(conductor.getId(),conductor.getNombre(), conductor.getCedula(), conductor.getTelefono(), conductor.getDireccion());
    }

    // Metodo para convertir una lista de Conductores a una lista de ConductorDTOs
    public List<ConductorDTO> entitiesToDTOs(List<Conductor> conductores) {
        return conductores.stream()
                .map(this::entityToDTO)  // Convierte cada conductor en ConductorDTO
                .collect(Collectors.toList());  // Colecta los resultados en una lista
    }

    // Metodo para convertir de ConductorDTO a Conductor
    public Conductor DTOToEntity(ConductorDTO conductorDTO) {
        Conductor conductor = new Conductor();
        conductor.setId(conductorDTO.getId());
        conductor.setNombre(conductorDTO.getNombre());
        conductor.setCedula(conductorDTO.getCedula());
        conductor.setTelefono(conductorDTO.getTelefono());
        conductor.setDireccion(conductorDTO.getDireccion());
        return conductor;
    }
}
