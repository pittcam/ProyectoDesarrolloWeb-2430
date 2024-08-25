package com.co.controller;

import com.co.model.Conductor;
import com.co.service.ConductorService;
import jakarta.validation.Valid;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("conductores")
public class ConductorController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ConductorService conductorService;

    // Obtener todos los conductores
    @GetMapping("/list")
    public ModelAndView listarConductores() {
        List<Conductor> conductores = conductorService.conductorList();
        ModelAndView modelAndView = new ModelAndView("conductor-list");
        modelAndView.addObject("conductores", conductores);
        return modelAndView;
    }

    @GetMapping("/view/{idConductor}")
    public ModelAndView verConductor(@PathVariable("idPersona") Long id) {
        Conductor conductor = conductorService.recuperarConductor(id);
        ModelAndView modelAndView = new ModelAndView("person-view");
        modelAndView.addObject("conductor", conductor);
        return modelAndView;
    }

    @GetMapping("/edit-form/{id}")
    public ModelAndView formularioEditarConductor(@PathVariable Long id) {
        Conductor c = conductorService.recuperarConductor(id);
        ModelAndView modelAndView = new ModelAndView("conductor-edit");
        modelAndView.addObject("conductor", c);
        return modelAndView;

    }

    @PostMapping(value = "/save")
    public Object guardarConductor(@Valid Conductor conductor, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("conductor-edit");
        }
        conductorService.guardarConductor(conductor);
        return new RedirectView("/conductor/list");
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

    @GetMapping("/search")
    public ModelAndView listConductores(@RequestParam(required = false) String searchText) {
        List<Conductor> conductors;
        if (searchText == null || searchText.trim().equals("")) {
            log.info("No hay texto de búsqueda. Retornando todo");
            conductors = conductorService.conductorList();
        } else {
            log.info("Buscando personas cuyo apellido comienza con {}", searchText);
            conductors = conductorService.buscarPorNombre(searchText);
        }
        ModelAndView modelAndView = new ModelAndView("person-search");
        modelAndView.addObject("conductors", conductors);
        return modelAndView;

    }
}
