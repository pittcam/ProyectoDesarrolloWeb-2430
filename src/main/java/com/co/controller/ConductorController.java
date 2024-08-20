package com.co.controller;

import com.co.model.Conductor;
import com.co.service.ConductorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/conductores")
public class ConductorController {

    @Autowired
    private ConductorService conductorService;

    // Obtener todos los conductores
    @GetMapping
    public ResponseEntity<List<Conductor>> getAllConductores() {
        List<Conductor> conductores = conductorService.findAll();
        return ResponseEntity.ok(conductores);
    }

    // Obtener un conductor por ID
    @GetMapping("/{id}")
    public ResponseEntity<Conductor> getConductorById(@PathVariable("id") Long id) {
        Optional<Conductor> conductor = conductorService.findById(id);
        return conductor.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear un nuevo conductor
    @PostMapping
    public ResponseEntity<Conductor> createConductor(@RequestBody Conductor conductor) {
        Conductor savedConductor = conductorService.save(conductor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedConductor);
    }

    // Actualizar un conductor existente
    @PutMapping("/{id}")
    public ResponseEntity<Conductor> updateConductor(@PathVariable("id") Long id, @RequestBody Conductor conductor) {
        if (!conductorService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        conductor.setId(id);
        Conductor updatedConductor = conductorService.save(conductor);
        return ResponseEntity.ok(updatedConductor);
    }

    // Eliminar un conductor por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConductor(@PathVariable("id") Long id) {
        if (!conductorService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        conductorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
