package com.co.service;

import com.co.conversion.ConductorDTOConverter;
import com.co.dto.ConductorDTO;
import com.co.model.Conductor;
import com.co.repository.ConductorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConductorService {

    @Autowired
    private ConductorRepository conductorRepository;

    @Autowired
    private ConductorDTOConverter conductorDTOConverter;

    // Obtener todos los conductores
    public List<ConductorDTO> getAllConductores() {
        return conductorRepository.findAll().stream()
                .map(conductorDTOConverter::entityToDTO)
                .toList();
    }

    // Obtener un conductor por ID
    public ConductorDTO getConductor(Long id) {
        return conductorDTOConverter.entityToDTO(
                conductorRepository.findById(id).orElseThrow(() -> new RuntimeException("Conductor no encontrado"))
        );
    }

    // Crear o actualizar un conductor
    public ConductorDTO saveConductor(ConductorDTO conductorDTO) {
        Conductor conductor = conductorDTOConverter.DTOToEntity(conductorDTO);
        return conductorDTOConverter.entityToDTO(conductorRepository.save(conductor));
    }

    // Eliminar un conductor por ID
    public void deleteConductor(Long id) {
        if (!conductorRepository.existsById(id)) {
            throw new RuntimeException("Conductor no encontrado para eliminar");
        }
        conductorRepository.deleteById(id);
    }
}
