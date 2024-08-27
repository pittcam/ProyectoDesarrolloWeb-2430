package com.co.controller;

import com.co.model.Bus;
import com.co.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bus")
public class BusController {

    @Autowired
    private BusService busService;

    @GetMapping("/list")
    public String listBuses(Model model) {
        List<Bus> buses = busService.findAll();
        model.addAttribute("buses", buses);
        return "bus-list";
    }

    @GetMapping("/view/{id}")
    public String viewBus(@PathVariable("id") Long id, Model model) {
        Bus bus = busService.findById(id).orElseThrow(() -> new RuntimeException("Bus no encontrado"));
        model.addAttribute("bus", bus);
        return "bus-view";
    }
}
