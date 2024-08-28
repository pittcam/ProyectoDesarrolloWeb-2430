package com.co.controller;

import com.co.model.Bus;
import com.co.model.Horario;
import com.co.model.Ruta;
import com.co.model.Asignacion;
import com.co.service.BusService;
import com.co.service.HorarioService;
import com.co.service.RutaService;
import com.co.service.AsignacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/sistema")
public class AsignacionController {

    @Autowired
    private AsignacionService asignacionService;
    @Autowired
    private BusService busService;
    @Autowired
    private HorarioService horarioService;
    @Autowired
    private RutaService rutaService;

    @GetMapping("/list")
    public ModelAndView listarSistemas() {
        List<Asignacion> asignaciones = asignacionService.obtenerTodos();
        ModelAndView modelAndView = new ModelAndView("sistema-list");
        modelAndView.addObject("sistemas", asignaciones);
        return modelAndView;
    }

    @GetMapping("/edit-form/{id}")
    public ModelAndView formularioEditarSistema(@PathVariable Long id) {
        Optional<Asignacion> asignacionOpt = asignacionService.obtenerPorId(id);
        if (!asignacionOpt.isPresent()) {
            // Manejar el caso cuando no se encuentra la asignación (opcional)
            return new ModelAndView("redirect:/sistema/list");
        }

        Asignacion asignacion = asignacionOpt.get();
        List<Bus> buses = busService.findAll();
        List<Horario> horarios = horarioService.findAll();
        List<Ruta> rutas = rutaService.obtenerTodos();

        ModelAndView modelAndView = new ModelAndView("sistema-form");
        modelAndView.addObject("sistema", asignacion);
        modelAndView.addObject("buses", buses);
        modelAndView.addObject("horarios", horarios);
        modelAndView.addObject("rutas", rutas);
        return modelAndView;
    }

    @PostMapping("/save")
    public RedirectView guardarSistema(@ModelAttribute Asignacion asignacion) {
        asignacionService.guardar(asignacion);
        return new RedirectView("/sistema/list");
    }

    @GetMapping("/delete/{id}")
    public RedirectView eliminarSistema(@PathVariable Long id) {
        asignacionService.eliminar(id);
        return new RedirectView("/sistema/list");
    }
}
