package com.co.controller;

import com.co.model.Bus;
import com.co.model.Conductor;
import com.co.service.BusService;
import com.co.service.ConductorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/bus")
public class BusController {

    @Autowired
    private BusService busService;

    @Autowired
    private ConductorService conductorService;

    @GetMapping("/list")
    public String listBuses(Model model) {
        model.addAttribute("buses", busService.findAll());
        return "bus-list";
    }

    @GetMapping("/view/{id}")
    public String viewBus(@PathVariable("id") Long id, Model model) {
        Bus bus = busService.findById(id).orElseThrow(() -> new RuntimeException("Bus no encontrado"));
        model.addAttribute("bus", bus);
        return "bus-view";
    }

    @GetMapping("/edit-form/{id}")
    public String editBusForm(@PathVariable("id") Long id, Model model) {
        Bus bus = busService.findById(id).orElseThrow(() -> new RuntimeException("Bus no encontrado"));
        List<Conductor> allConductors = conductorService.conductorList();

        model.addAttribute("bus", bus);
        model.addAttribute("allConductors", allConductors);
        return "bus-form";
    }

    @GetMapping("/add-form")
    public String addBusForm(Model model) {
        model.addAttribute("bus", new Bus());
        model.addAttribute("allConductors", conductorService.conductorList());
        return "bus-form";
    }

    @PostMapping("/save")
    public String saveBus(@ModelAttribute Bus bus) {
        busService.save(bus);
        return "redirect:/bus/list";
    }

    @PostMapping("/assign")
    public String assignConductorsToBus(
            @RequestParam("busId") Long busId,
            @RequestParam("conductors") List<Long> conductorIds) {

        Bus bus = busService.findById(busId).orElseThrow(() -> new RuntimeException("Bus no encontrado"));
        List<Conductor> conductors = conductorService.findByIds(conductorIds);

        bus.setConductores(new HashSet<>(conductors));
        busService.save(bus);

        return "redirect:/bus/list";
    }
}
