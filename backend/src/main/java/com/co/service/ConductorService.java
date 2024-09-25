package com.co.service;

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

    // Crear o actualizar un conductor
    public void guardarConductor(ConductorDTO conductor) {
        return conductorRepository.save(conductor);
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
