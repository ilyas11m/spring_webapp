package com.project.webapp.controller;

import com.project.webapp.model.Airplane;
import com.project.webapp.service.AirplaneService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AirplaneController {

    private final AirplaneService airplaneService;

    public AirplaneController(AirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }

    @GetMapping("/airplane_flight_page")
    public String getFlightPage() {
        return "redirect:/flight";
    }

    @GetMapping("/airplane_ticket_page")
    public String getTicketPage() {
        return "redirect:/ticket";
    }

    @GetMapping("/airplane_passenger_page")
    public String getPassengerPage() {
        return "redirect:/passenger";
    }

    @GetMapping("/airplane")
    public String findAllAirplanes(Model model) {
        List<Airplane> allAirplanes = airplaneService.findAllAirplanes();
        model.addAttribute("airplanes", allAirplanes);
        return "airplanes";
    }

    @PostMapping("/airplane")
    public String addAirplane(@RequestParam String model, @RequestParam int capacity,
                              @RequestParam int distance, Model modelAttribute) {
        Airplane airplane = new Airplane();
        airplane.setModel(model);
        airplane.setCapacity(capacity);
        airplane.setDistance(distance);
        airplaneService.addAirplane(airplane);

        List<Airplane> allAirplanes = airplaneService.findAllAirplanes();
        modelAttribute.addAttribute("airplanes", allAirplanes);

        return "airplanes";
    }

    @PostMapping("/airplane_edit")
    public String editAirplane(@RequestParam Long airplaneId,
                               @RequestParam(required = false) String newModel,
                               @RequestParam(required = false) Integer newCapacity,
                               @RequestParam(required = false) Integer newDistance,
                               Model model) {
        if ((newModel == null || newModel.isEmpty()) && newCapacity == null && newDistance == null) {
            throw new IllegalArgumentException("At least one field must be provided for update.");
        }

        airplaneService.updateAirplane(airplaneId, newModel, newCapacity, newDistance);
        List<Airplane> airplanes = airplaneService.findAllAirplanes();
        model.addAttribute("airplanes", airplanes);

        return "redirect:/airplane";
    }

    @PostMapping("/airplane_delete_by_id")
    public String deleteById(@RequestParam Long airplaneId, Model model) {
        airplaneService.deleteById( airplaneId);
        List<Airplane> airplanes = airplaneService.findAllAirplanes();
        model.addAttribute("airplanes", airplanes);
        return "redirect:/airplane";
    }

    @PostMapping("/airplane_delete_all")
    public String deleteAll(Model model) {
        airplaneService.deleteAll();
        List<Airplane> allAirplanes = airplaneService.findAllAirplanes();
        model.addAttribute("airplanes", allAirplanes);
        return "redirect:/airplane";
    }
}
