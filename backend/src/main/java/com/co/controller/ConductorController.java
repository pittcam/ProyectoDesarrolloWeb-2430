package com.co.controller;

import com.co.conversion.ConductorDTOConverter;
import com.co.dto.ConductorDTO;
import com.co.model.*;
import com.co.service.AsignacionService;
import com.co.service.BusService;
import com.co.service.ConductorService;
import com.co.service.HorarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/conductor")
public class ConductorController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ConductorService conductorService;

    @Autowired
    private BusService busService;

    @Autowired
    private HorarioService horarioService;

    @Autowired
    private AsignacionService asignacionService;

    @Autowired
    private ConductorDTOConverter conductorDTOConverter;

    // Obtener todos los conductores
    @GetMapping
    public List<Conductor> listarConductores() {
        List<Conductor> conductores = conductorService.conductorList();
        return conductores;
    }


    //Buscar Conductor por nombre
    @GetMapping("/search")
    public ResponseEntity<List<ConductorDTO>> buscarConductoresPorNombre(@RequestParam(required = false) String searchText) {
        List<Conductor> conductores;
        
        if (searchText == null || searchText.trim().isEmpty()) {
            // Si no se ingresa un nombre, se devuelven todos los conductores
            conductores = conductorService.conductorList();
        } else {
            // Si se ingresa un nombre, se filtran los conductores por nombre
            conductores = conductorService.buscarPorNombre(searchText);
        }
        
        List<ConductorDTO> conductoresDTO = conductores.stream()
                                        .map(conductorDTOConverter::entityToDTO)
                                        .collect(Collectors.toList());
        
        return ResponseEntity.ok(conductoresDTO);
    }

    //Crear conductor
    @PostMapping
    public ResponseEntity<ConductorDTO> crearConductor(@RequestBody @Valid ConductorDTO conductorDTO) {
        ConductorDTO nuevoConductor = conductorService.agregarConductor(conductorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoConductor);
    }



    // Editar
    @PutMapping("/{id}")
    public ResponseEntity<ConductorDTO> editarConductor(@PathVariable Long id, @RequestBody @Valid ConductorDTO conductorDTO) {
        Conductor conductorExistente = conductorService.recuperarConductor(id);
        
        // Actualizar campos
        conductorExistente.setNombre(conductorDTO.getNombre());
        conductorExistente.setCedula(conductorDTO.getCedula());
        conductorExistente.setTelefono(conductorDTO.getTelefono());
        conductorExistente.setDireccion(conductorDTO.getDireccion());
        
        // Guardar conductor actualizado
        conductorService.guardarConductor(conductorDTOConverter.entityToDTO(conductorExistente)); // O convertir a DTO si el servicio lo requiere

        ConductorDTO conductorActualizadoDTO = conductorDTOConverter.entityToDTO(conductorExistente);

        return ResponseEntity.ok(conductorActualizadoDTO);
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarConductor(@PathVariable Long id) {
        if (conductorService.existsById(id)) {
            conductorService.delete(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content si se elimina correctamente
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 si no existe
        }
    }

    //Ver conductor
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> verConductor(@PathVariable Long id) {
        Conductor conductor = conductorService.recuperarConductor(id);
        List<Asignacion> asignaciones = asignacionService.findByConductorId(id);

        // Mapea las asignaciones a la información de los buses
        List<Map<String, Object>> busesAsignados = asignaciones.stream().map(asignacion -> {
            Bus bus = asignacion.getBus();
            Horario horario = asignacion.getHorario();
            Map<String, Object> busInfo = Map.of(
                    "placa", bus.getNumeroPlaca(), // Cambiado a getNumeroPlaca()
                    "modelo", bus.getModelo(),
                    "diasAsignados", horario.getDias() // Asegúrate de que "dias" exista en Horario
            );
            return busInfo;
        }).collect(Collectors.toList());

        Map<String, Object> response = Map.of(
                "conductor", conductorDTOConverter.entityToDTO(conductor),
                "busesAsignados", busesAsignados
        );

        return ResponseEntity.ok(response);
    }


    @PostMapping("/{id}/asignar-buses")
    public ResponseEntity<Void> asignarBuses(
            @PathVariable Long id,
            @RequestBody Map<Long, Long> busHorarioMap) { // Mapa de busId a horarioId
        Conductor conductor = conductorService.recuperarConductor(id);

        // Eliminar asignaciones anteriores
        asignacionService.deleteByConductorId(id);

        // Asignar los nuevos buses
        busHorarioMap.forEach((busId, horarioId) -> {
            Bus bus = busService.findById(busId)
                    .orElseThrow(() -> new RuntimeException("Bus no encontrado"));

            Horario horario = horarioService.findById(horarioId)
                    .orElseThrow(() -> new RuntimeException("Horario no encontrado"));

            Asignacion asignacion = new Asignacion();
            asignacion.setConductor(conductor);
            asignacion.setBus(bus);
            asignacion.setHorario(horario);

            asignacionService.guardar(asignacion);
        });

        return ResponseEntity.ok().build();
    }
}
