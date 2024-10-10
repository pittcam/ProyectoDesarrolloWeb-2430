package com.co.controller;

import com.co.dto.HorarioDTO;
import com.co.dto.RutaDTO;
import com.co.model.Asignacion;
import com.co.model.Bus;
import com.co.service.AsignacionService;
import com.co.service.BusService;
import com.co.service.HorarioService;
import com.co.service.RutaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/asignacion")
public class AsignacionController {

    @Autowired
    private AsignacionService asignacionService;

    @Autowired
    private BusService busService;

    @Autowired
    private HorarioService horarioService;

    @Autowired
    private RutaService rutaService;

    // Listar todas las asignaciones
    @GetMapping
    public List<Asignacion> listarAsignaciones() {
        return asignacionService.obtenerTodos();
    }

    // Obtener una asignación por ID
    @GetMapping("/{id}")
    public Asignacion obtenerAsignacion(@PathVariable Long id) {
        Optional<Asignacion> asignacionOpt = asignacionService.obtenerPorId(id);
        return asignacionOpt.orElse(null); // Devuelve la asignación o null si no existe
    }

    // Crear una nueva asignación
    @PostMapping
    public Asignacion crearAsignacion(@Valid @RequestBody Asignacion asignacion) {
        return asignacionService.guardar(asignacion);
    }

    // Actualizar una asignación existente
    @PutMapping("/{id}")
    public Asignacion actualizarAsignacion(@PathVariable Long id, @Valid @RequestBody Asignacion asignacionActualizada) {
        Optional<Asignacion> asignacionOpt = asignacionService.obtenerPorId(id);
        if (asignacionOpt.isPresent()) {
            Asignacion asignacionExistente = asignacionOpt.get();
            // Aquí se puede copiar los campos de asignacionActualizada a asignacionExistente
            return asignacionService.guardar(asignacionExistente);
        } else {
            return null; // Manejar el caso donde la asignación no exista
        }
    }

    // Eliminar una asignación
    @DeleteMapping("/{id}")
    public void eliminarAsignacion(@PathVariable Long id) {
        asignacionService.eliminar(id);
    }

    // Obtener listas de buses, horarios y rutas (para usar en el frontend)
    @GetMapping("/buses")
    public List<Bus> listarBuses() {
        return busService.getAllBuses();
    }

    @GetMapping("/horarios")
    public List<HorarioDTO> listarHorarios() {
        return horarioService.obtenerHorarios();
    }

    @GetMapping("/rutas")
    public List<RutaDTO> listarRutas() {
        return rutaService.listarRutas();
    }
}
