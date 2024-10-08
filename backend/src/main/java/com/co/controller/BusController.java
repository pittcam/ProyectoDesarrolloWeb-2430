package com.co.controller;

import com.co.conversion.BusDTOConverter;
import com.co.dto.BusDTO;
import com.co.dto.ConductorDTO;
import com.co.model.Bus;
import com.co.model.Conductor;
import com.co.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bus")
public class BusController {

    @Autowired
    private BusService busService;

    @Autowired
    private BusDTOConverter busDTOConverter;

    // Obtener todos los buses
    @GetMapping
    public List<Bus> recuperarBuses() throws InterruptedException {
        Thread.sleep(2000);
        List<Bus> buses = busService.getAllBuses();
        return buses;
    }

    // Obtener un bus por ID
    @GetMapping("/{id}")
    public BusDTO recuperarBus(@PathVariable Long id) {
        BusDTO busDTO = busService.getBus(id); // Cambia esto para que use BusDTO
        return busDTO;
    }

    // Crear un nuevo bus
    @PostMapping
    public BusDTO crearBus(@RequestBody BusDTO busDTO) {
        return busService.createBus(busDTO);
    }

    // Actualizar un bus
    @PutMapping("/{id}")
    public ResponseEntity<BusDTO> actualizarBus(@PathVariable Long id, @RequestBody BusDTO busDTO) {
        busDTO.setId(id);  // Suponiendo que el DTO tiene un método setId
        BusDTO actualizadoBusDTO = busService.save(busDTO); // Cambia esto para que use BusDTO
        return ResponseEntity.ok(actualizadoBusDTO);
    }

    // Eliminar un bus
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarBus(@PathVariable Long id) {
        busService.delete(id);
        return ResponseEntity.noContent().build();
    }
}