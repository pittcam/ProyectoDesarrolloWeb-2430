package com.co.service;

import com.co.model.Bus;
import com.co.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    // Obtener todos los buses
    public List<Bus> findAll() {
        return busRepository.findAll();
    }

    // Obtener un bus por ID
    public Optional<Bus> findById(Long id) {
        return busRepository.findById(id);
    }

    // Crear o actualizar un bus
    public void save(Bus bus) {
        busRepository.save(bus);
    }

    // Eliminar un bus por ID
    public void delete(Long id) {
        busRepository.deleteById(id);
    }
}
