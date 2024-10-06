package com.co.service;

import com.co.conversion.ConductorDTOConverter;
import com.co.dto.ConductorDTO;
import com.co.model.Conductor;
import com.co.repository.ConductorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConductorService {

    @Autowired
    private ConductorRepository conductorRepository;

    @Autowired
    private ConductorDTOConverter conductorDTOConverter;

    // Obtener todos los conductores
    public List<ConductorDTO> conductorList() {
        return conductorRepository.findAll().stream()
                .map(conductorDTOConverter::entityToDTO)
                .collect(Collectors.toList());
    }

    // Buscar conductores por nombre
    public List<ConductorDTO> buscarPorNombre(String textoBusqueda) {
        return conductorRepository.findAllByNombreContainingIgnoreCase(textoBusqueda).stream()
                .map(conductorDTOConverter::entityToDTO)
                .collect(Collectors.toList());
    }

    // Obtener un conductor por ID
    public ConductorDTO recuperarConductor(Long id) {
        Conductor conductor = conductorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conductor no encontrado"));
        return conductorDTOConverter.entityToDTO(conductor);
    }

    // Crear o actualizar un conductor
    public void guardarConductor(ConductorDTO conductorDTO) {
        Conductor conductor = conductorDTOConverter.DTOToEntity(conductorDTO);
        conductorRepository.save(conductor);
    }

    // Eliminar un conductor por ID
    public void delete(Long id) {
        conductorRepository.deleteById(id);
    }

    // Verificar si un conductor existe por ID
    public boolean existsById(Long id) {
        return conductorRepository.existsById(id);
    }

    // Obtener conductores por lista de IDs
    public List<ConductorDTO> findByIds(List<Long> ids) {
        return conductorRepository.findAllById(ids).stream()
                .map(conductorDTOConverter::entityToDTO)
                .collect(Collectors.toList());
    }
}
