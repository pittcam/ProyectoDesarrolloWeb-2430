package com.co.controller;

import com.co.model.Bus;
import com.co.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bus")
public class BusController {

    @Autowired
    private BusService busService;

    @GetMapping("/list")
    public String listBuses(Model model) {
        model.addAttribute("buses", busService.findAll());
        return "bus-list";
    }

    @GetMapping("/view/{id}")
    public String viewBus(@PathVariable("id") Long id, Model model) {
        Bus bus = busService.findById(id).orElseThrow();
        model.addAttribute("bus", bus);
        return "bus-view";
    }

    @GetMapping("/edit-form/{id}")
    public String editBusForm(@PathVariable("id") Long id, Model model) {
        Bus bus = busService.findById(id).orElseThrow();
        model.addAttribute("bus", bus);
        return "bus-form";
    }

    @GetMapping("/add-form")
    public String addBusForm(Model model) {
        model.addAttribute("bus", new Bus());
        return "bus-form";
    }

    @PostMapping("/save")
    public String saveBus(@ModelAttribute Bus bus) {
        busService.save(bus);
        return "redirect:/bus/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteBus(@PathVariable("id") Long id) {
        busService.delete(id);
        return "redirect:/bus/list";
    }

    @GetMapping("/search")
    public String searchBuses(@RequestParam("searchText") String searchText, Model model) {
        // Implement search logic if needed
        model.addAttribute("buses", busService.findAll()); // Adjust search results as necessary
        return "bus-search";
    }
}
