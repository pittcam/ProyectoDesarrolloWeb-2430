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
    public List<Conductor> conductorList() {
        return conductorRepository.findAll();
    }

    // Buscar conductores por nombre
    public List<Conductor> buscarPorNombre(String textoBusqueda) {
        return conductorRepository.findAllByNombreContainingIgnoreCase(textoBusqueda);
    }

    // Obtener un conductor por ID
    public Conductor recuperarConductor(Long id) {
        return conductorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conductor no encontrado"));
    }

    public ConductorDTO agregarConductor(ConductorDTO conductorDTO) {
        // Convertir DTO a entidad
        Conductor nuevoConductor = conductorDTOConverter.DTOToEntity(conductorDTO);
        
        // Guardar el conductor en la base de datos
        Conductor conductorGuardado = conductorRepository.save(nuevoConductor);
        
        // Convertir la entidad guardada a DTO para devolver
        return conductorDTOConverter.entityToDTO(conductorGuardado);
    }

    // Crear o actualizar un conductor
    // Crear o actualizar un conductor
    public ConductorDTO guardarConductor(ConductorDTO conductorDTO) {
        // Convertir DTO a entidad
        Conductor nuevoConductor = conductorDTOConverter.DTOToEntity(conductorDTO);
        
        // Guardar el conductor en la base de datos
        Conductor conductorGuardado = conductorRepository.save(nuevoConductor);
        
        // Convertir la entidad guardada a DTO para devolver
        return conductorDTOConverter.entityToDTO(conductorGuardado);
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
    public List<Conductor> findByIds(List<Long> ids) {
        return conductorRepository.findAllById(ids);
    }
}
