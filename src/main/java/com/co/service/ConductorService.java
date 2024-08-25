package com.co.service;

import com.co.model.Conductor;
import com.co.repository.ConductorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConductorService {

    @Autowired
    private ConductorRepository conductorRepository;



    // Obtener todos los conductores
    public List<Conductor> conductorList() {
        return conductorRepository.findAll();
    }

    public List<Conductor> buscarPorNombre(String textoBusqueda) {
        return conductorRepository.findAllByNombre(textoBusqueda);
        //return personRepository.findAllByLastNameStartingWith(textoBusqueda);
        // return personRepository.findAllByLastNameStartingWithIgnoreCase(textoBusqueda);
        // return personRepository.findPersonsByLastNameStartingWithCaseInsensitive(textoBusqueda);
        // return personRepository.findPersonsByLastNameStartingWith(textoBusqueda);
    }

    public Conductor recuperarConductor(Long id) {
        return conductorRepository.findById(id).orElseThrow();
    }




    // Obtener un conductor por ID
    public Optional<Conductor> findById(Long id) {
        return conductorRepository.findById(id);
    }

    // Crear o actualizar un conductor
    public void guardarConductor(Conductor conductor) {
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
}

