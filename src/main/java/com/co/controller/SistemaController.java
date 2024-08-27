package com.co.controller;

import com.co.model.Sistema;
import com.co.service.SistemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/sistema")
public class SistemaController {

    @Autowired
    private SistemaService sistemaService;

    @GetMapping("/list")
    public ModelAndView listarSistemas() {
        List<Sistema> sistemas = sistemaService.obtenerTodos();
        ModelAndView modelAndView = new ModelAndView("sistema-list");
        modelAndView.addObject("sistemas", sistemas);
        return modelAndView;
    }

    @GetMapping("/edit-form/{id}")
    public ModelAndView formularioEditarSistema(@PathVariable Long id) {
        Sistema sistema = sistemaService.obtenerPorId(id).orElseThrow();
        ModelAndView modelAndView = new ModelAndView("sistema-form");
        modelAndView.addObject("sistema", sistema);
        return modelAndView;
    }

    @PostMapping("/save")
    public RedirectView guardarSistema(@ModelAttribute Sistema sistema) {
        sistemaService.guardar(sistema);
        return new RedirectView("/sistema/list");
    }

    @GetMapping("/delete/{id}")
    public RedirectView eliminarSistema(@PathVariable Long id) {
        sistemaService.eliminar(id);
        return new RedirectView("/sistema/list");
    }
}
