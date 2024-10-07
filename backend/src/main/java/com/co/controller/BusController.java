package com.co.controller;

import com.co.model.Bus;
import com.co.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bus")
public class BusController {

    @Autowired
    private BusService busService;

    // Método para listar todos los buses
    @GetMapping("/list")
    public String listBuses(Model model) {
        List<Bus> buses = busService.findAll();
        model.addAttribute("buses", buses);
        return "bus-list"; // Asegúrate de que este archivo existe en src/main/resources/templates/bus/
    }

    // Método para ver detalles de un bus específico
    @GetMapping("/view/{id}")
    public String viewBus(@PathVariable("id") Long id, Model model) {
        Bus bus = busService.findById(id).orElseThrow(() -> new RuntimeException("Bus no encontrado"));
        model.addAttribute("bus", bus);
        return "bus-view";
    }

    @GetMapping("/logout")
    public String logout(Model model) {

        return "logout";
    }
}
