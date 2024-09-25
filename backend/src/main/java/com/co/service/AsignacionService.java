package com.co.service;

import com.co.model.Asignacion;
import com.co.model.Conductor;
import com.co.model.Bus;
import com.co.repository.AsignacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsignacionService {

    @Autowired
    private AsignacionRepository asignacionRepository;

    public List<Asignacion> findByConductor(Conductor conductor) {
        return asignacionRepository.findByConductor(conductor);
    }

    // Obtener asignaciones por ID del conductor
    public List<Asignacion> findByConductorId(Long conductorId) {
        return asignacionRepository.findByConductorId(conductorId);
    }

    public List<Asignacion> findByBus(Bus bus) {
        return asignacionRepository.findByBus(bus);
    }

    public List<Asignacion> obtenerTodos() {
        return asignacionRepository.findAll();
    }

    public Optional<Asignacion> obtenerPorId(Long id) {
        return asignacionRepository.findById(id);
    }

    public Asignacion guardar(Asignacion asignacion) {
        return asignacionRepository.save(asignacion);
    }

    public void eliminar(Long id) {
        asignacionRepository.deleteById(id);
    }

    public void deleteByConductorId(Long conductorId) {
        List<Asignacion> asignaciones = asignacionRepository.findByConductorId(conductorId);
        asignacionRepository.deleteAll(asignaciones);
    }
}
