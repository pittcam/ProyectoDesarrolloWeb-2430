package com.co.controller;

import com.co.dto.ConductorDTO;
import com.co.service.ConductorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conductor")
public class ConductorController {

    @Autowired
    private ConductorService conductorService;

    // http://localhost:8080/conductor
    @GetMapping
    public ResponseEntity<List<ConductorDTO>> recuperarConductores() {
        List<ConductorDTO> conductores = conductorService.getAllConductores();
        return ResponseEntity.ok(conductores);
    }

    // http://localhost:8080/conductor/1
    @GetMapping("/{id}")
    public ResponseEntity<ConductorDTO> recuperarConductor(@PathVariable Long id) {
        ConductorDTO conductorDTO = conductorService.getConductor(id);
        return ResponseEntity.ok(conductorDTO);
    }

    @PostMapping
    public ResponseEntity<ConductorDTO> crearConductor(@RequestBody ConductorDTO conductorDTO) {
        ConductorDTO nuevoConductor = conductorService.saveConductor(conductorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoConductor);
    }

    // Idempotency
    @PutMapping("/{id}")
    public ResponseEntity<ConductorDTO> actualizarConductor(@PathVariable Long id, @RequestBody ConductorDTO conductorDTO) {
        conductorDTO.setId(id);  // Suponiendo que el DTO tiene un método setId
        ConductorDTO actualizado = conductorService.saveConductor(conductorDTO);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar conductor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarConductor(@PathVariable Long id) {
        conductorService.deleteConductor(id);
        return ResponseEntity.noContent().build();
    }
}
