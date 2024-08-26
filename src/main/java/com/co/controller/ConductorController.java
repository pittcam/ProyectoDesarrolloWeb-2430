package com.co.controller;

import com.co.model.Conductor;
import com.co.service.ConductorService;
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

@Controller
@RequestMapping("/conductor")
public class ConductorController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ConductorService conductorService;

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
        return modelAndView;
    }

    // Mostrar el formulario de agregar conductor
    @GetMapping("/add-form")
    public ModelAndView formularioAgregarConductor() {
        Conductor nuevoConductor = new Conductor();  // Creamos un nuevo objeto Conductor
        ModelAndView modelAndView = new ModelAndView("conductor-form");  // Referencia a la vista del formulario
        modelAndView.addObject("conductor", nuevoConductor);  // Pasamos el objeto Conductor al modelo
        return modelAndView;
    }

    @PostMapping("/save")
    public Object guardarConductor(@Valid @ModelAttribute Conductor conductor, BindingResult result) {
        if (result.hasErrors()) {
            // Si hay errores, regresa al formulario adecuado.
            if (conductor.getId() == null) {
                return new ModelAndView("conductor-form"); // Si no hay ID, es agregar.
            } else {
                return new ModelAndView("conductor-form"); // Si hay ID, es editar.
            }
        }

        conductorService.guardarConductor(conductor);
        return new RedirectView("/conductor/list"); // Redirige a la lista después de guardar.
    }

    @GetMapping("/view/{id}")
    public ModelAndView verConductor(@PathVariable("id") Long id) {
        Conductor conductor = conductorService.recuperarConductor(id);
        ModelAndView modelAndView = new ModelAndView("conductor-view");
        modelAndView.addObject("conductor", conductor);
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
}
