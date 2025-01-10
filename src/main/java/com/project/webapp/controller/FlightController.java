package com.project.webapp.controller;

import com.project.webapp.model.Airplane;
import com.project.webapp.model.Flight;
import com.project.webapp.service.AirplaneService;
import com.project.webapp.service.FlightService;
import com.project.webapp.service.PassengerService;
import com.project.webapp.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FlightController {

    private final FlightService flightService;
    private final AirplaneService airplaneService;

    @GetMapping("/flight_airplane_page")
    public String getAirplanePage(Model model) {
        return "redirect:/airplane";
    }

    @GetMapping("/flight_ticket_page")
    public String getTicketPage() {
        return "redirect:/ticket";
    }

    @GetMapping("/flight_passenger_page")
    public String getPassengerPage() {
        return "redirect:/passenger";
    }

    public FlightController(FlightService flightService, AirplaneService airplaneService) {
        this.flightService = flightService;
        this.airplaneService = airplaneService;
    }

    @GetMapping("/flight")
    public String findAllFlights(Model model) {
        List<Flight> flights = flightService.findAll();
        model.addAttribute("flights", flights);
        return "flights";
    }

    @PostMapping("/flight")
    public String addFlight(@RequestParam String flightNumber, @RequestParam String departureLocation,
                            @RequestParam String arrivalLocation, @RequestParam int durationMinutes,
                            @RequestParam Long airplaneId, Model model) {
        Airplane airplane = airplaneService.findById(airplaneId);
        Flight flight = new Flight();
        flight.setFlightNumber(flightNumber);
        flight.setDepartureLocation(departureLocation);
        flight.setArrivalLocation(arrivalLocation);
        flight.setDurationMinutes(durationMinutes);
        flight.setAirplane(airplane);
        flightService.addFlight(flight);

        List<Flight> flights = flightService.findAll();
        model.addAttribute("flights", flights);

        return "flights";
    }

    @PostMapping("/flight_delete_by_id")
    public String deleteById(@RequestParam Long flightId, Model model ) {
        flightService.deleteById(flightId);
        List<Flight> flights = flightService.findAll();
        model.addAttribute("flights", flights);
        return "flights";
    }

    @PostMapping("/flight_delete_all")
    public String deleteAll(Model model) {
        flightService.deleteAll();
        List<Flight> flights = flightService.findAll();
        model.addAttribute("flights", flights);
        return "flights";
    }

}
