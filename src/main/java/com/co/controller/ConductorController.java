package com.co.controller;

import com.co.model.*;
import com.co.service.AsignacionService;
import com.co.service.BusService;
import com.co.service.ConductorService;
import com.co.service.HorarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
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

    // Obtener todos los conductores
    @GetMapping("/list")
    public ModelAndView listarConductores() {
        List<Conductor> conductores = conductorService.conductorList();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("conductores", conductores);
        return modelAndView;
    }

    // Editar
    @GetMapping("/edit-form/{id}")
    public ModelAndView formularioEditarConductor(@PathVariable Long id) {
        Conductor c = conductorService.recuperarConductor(id);
        ModelAndView modelAndView = new ModelAndView("conductor-form");
        modelAndView.addObject("conductor", c);
        modelAndView.addObject("buses", busService.findAll());
        modelAndView.addObject("horarios", horarioService.findAll());
        return modelAndView;
    }

    // Mostrar el formulario de agregar conductor
    @GetMapping("/add-form")
    public ModelAndView formularioAgregarConductor() {
        Conductor nuevoConductor = new Conductor();
        ModelAndView modelAndView = new ModelAndView("conductor-form");
        modelAndView.addObject("conductor", nuevoConductor);
        return modelAndView;
    }

    @PostMapping("/save")
    public Object guardarConductor(@Valid @ModelAttribute Conductor conductor, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("conductor-form");
        }

        conductorService.guardarConductor(conductor);
        return new RedirectView("/conductor/list");
    }

    @GetMapping("/view/{id}")
    public ModelAndView verConductor(@PathVariable Long id) {
        Optional<Conductor> conductorOpt = Optional.ofNullable(conductorService.recuperarConductor(id));
        if (!conductorOpt.isPresent()) {
            // Manejar el caso cuando no se encuentra el conductor
            return new ModelAndView("redirect:/conductor/list");
        }

        Conductor conductor = conductorOpt.get();
        List<Asignacion> asignaciones = asignacionService.findByConductorId(id);

        ModelAndView modelAndView = new ModelAndView("conductor-view");
        modelAndView.addObject("conductor", conductor);
        modelAndView.addObject("asignaciones", asignaciones);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public RedirectView deleteConductor(@PathVariable("id") Long id) {
        if (conductorService.existsById(id)) {
            conductorService.delete(id);
            log.info("Conductor con ID {} eliminado correctamente.", id);
        } else {
            log.warn("El conductor con ID {} no fue encontrado.", id);
        }
        return new RedirectView("/conductor/list");
    }

    @GetMapping("/search")
    public ModelAndView listConductores(@RequestParam(required = false) String searchText) {
        List<Conductor> conductores;
        if (searchText == null || searchText.trim().equals("")) {
            log.info("No hay texto de búsqueda. Retornando todo");
            conductores = conductorService.conductorList();
        } else {
            log.info("Buscando conductores cuyo nombre comienza con {}", searchText);
            conductores = conductorService.buscarPorNombre(searchText);
        }
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("conductores", conductores);
        return modelAndView;
    }

    @GetMapping("/assign-buses/{id}")
    public ModelAndView asignarBuses(@PathVariable Long id) {
        Conductor conductor = conductorService.recuperarConductor(id);
        List<Bus> buses = busService.findAll();
        List<Horario> horarios = horarioService.findAll();

        ModelAndView modelAndView = new ModelAndView("assign-buses");
        modelAndView.addObject("conductor", conductor);
        modelAndView.addObject("buses", buses);
        modelAndView.addObject("horarios", horarios);
        return modelAndView;
    }

    @PostMapping("/save-bus-assignments")
    public RedirectView guardarAsignaciones(@RequestParam("conductorId") Long conductorId,
                                            @RequestParam("busIds") List<Long> busIds,
                                            @RequestParam Map<String, String> allParams) {

        // Obtener el conductor
        Conductor conductor = conductorService.recuperarConductor(conductorId);

        // Eliminar asignaciones existentes
        asignacionService.deleteByConductorId(conductorId);

        // Crear nuevas asignaciones
        for (Long busId : busIds) {
            String horarioIdParam = "horarioIds_" + busId;
            String horarioIdStr = allParams.get(horarioIdParam);
            if (horarioIdStr != null) {
                Long horarioId = Long.parseLong(horarioIdStr);
                Bus bus = busService.findById(busId).orElseThrow(() -> new RuntimeException("Bus no encontrado"));
                Horario horario = horarioService.findById(horarioId).orElseThrow(() -> new RuntimeException("Horario no encontrado"));

                Asignacion asignacion = new Asignacion();
                asignacion.setBus(bus);
                asignacion.setHorario(horario);
                asignacion.setConductor(conductor);
                asignacionService.guardar(asignacion);
            }
        }

        return new RedirectView("/conductor/list");
    }

    @GetMapping("/assign-schedule/{id}")
    public ModelAndView asignarHorarios(@PathVariable Long id) {
        Conductor conductor = conductorService.recuperarConductor(id);
        List<Horario> horarios = horarioService.findAll();
        ModelAndView modelAndView = new ModelAndView("assign-schedule");
        modelAndView.addObject("conductor", conductor);
        modelAndView.addObject("horarios", horarios);
        return modelAndView;
    }

    @PostMapping("/save-schedule-assignments")
    public RedirectView guardarAsignacionesHorarios(
            @RequestParam Long conductorId,
            @RequestParam List<Long> horarioIds) {
        Conductor conductor = conductorService.recuperarConductor(conductorId);
        log.info("Conductor recuperado: {}", conductor);

        List<Horario> horarios = horarioService.findByIds(horarioIds);
        log.info("Horarios recuperados: {}", horarios);

        // Crear nuevas asignaciones para los horarios seleccionados
        horarios.forEach(horario -> {
            Asignacion asignacion = new Asignacion();
            asignacion.setConductor(conductor);
            asignacion.setHorario(horario);
            asignacionService.guardar(asignacion);
        });

        log.info("Conductor actualizado con horarios asignados: {}", conductor);
        return new RedirectView("/conductor/list");
    }
}
