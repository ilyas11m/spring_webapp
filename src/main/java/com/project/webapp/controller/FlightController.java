package com.project.webapp.controller;

import com.project.webapp.model.Airplane;
import com.project.webapp.model.Flight;
import com.project.webapp.service.AirplaneService;
import com.project.webapp.service.FlightService;
import com.project.webapp.service.PassengerService;
import com.project.webapp.service.TicketService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FlightController {

    private final FlightService flightService;
    private final AirplaneService airplaneService;

    public FlightController(FlightService flightService, AirplaneService airplaneService) {
        this.flightService = flightService;
        this.airplaneService = airplaneService;
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/flight_airplane_page")
    public String getAirplanePage(Model model) {
        return "redirect:/airplane";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/flight_ticket_page")
    public String getTicketPage() {
        return "redirect:/ticket";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/flight_passenger_page")
    public String getPassengerPage() {
        return "redirect:/passenger";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/flight")
    public String findAllFlights(Model model) {
        List<Flight> flights = flightService.findAll();
        model.addAttribute("flights", flights);
        return "flights";
    }

    @GetMapping("/flight_back")
    public String getFlightPage() {
        return "redirect:/flight";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/flight_find")
    public String findFlight(@RequestParam(required = false) String flightNumber,
                             @RequestParam(required = false) String departureLocation,
                             @RequestParam(required = false) String arrivalLocation,
                             @RequestParam(required = false) Integer durationMinutes,
                             Model model) {
        List<Flight> foundFlights = flightService.findAll();
        if (flightNumber != null && !flightNumber.isEmpty()) {
            foundFlights = foundFlights.stream().filter(flight -> flight.getFlightNumber()
                    .equalsIgnoreCase(flightNumber)).toList();
        }
        if (departureLocation != null && !departureLocation.isEmpty()) {
            foundFlights = foundFlights.stream().filter(flight -> flight.getDepartureLocation().
                    equalsIgnoreCase(departureLocation)).toList();
        }
        if (arrivalLocation != null && !arrivalLocation.isEmpty()) {
            foundFlights = foundFlights.stream().filter(flight -> flight.getArrivalLocation().
                    equalsIgnoreCase(arrivalLocation)).toList();
        }
        if (durationMinutes != null && durationMinutes > 0) {
            foundFlights = foundFlights.stream().filter(flight -> flight.getDurationMinutes().
                    equals(durationMinutes)).toList();
        }
        model.addAttribute("flights", foundFlights);
        return "flights";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
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

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/flight_edit")
    public String editFlight(@RequestParam Long flightId,
                             @RequestParam(required = false) String newNumber,
                             @RequestParam(required = false) String newDeparture,
                             @RequestParam(required = false) String newArrival,
                             @RequestParam(required = false) Integer newDuration,
                             Model model) {
        if ((newNumber == null || newNumber.isEmpty()) &&
                (newDeparture == null || newDeparture.isEmpty()) &&
                (newArrival == null || newArrival.isEmpty()) &&
                (newDuration == null)) {
            throw new IllegalArgumentException("At least one filed must be provided for update");
        }

        flightService.updateFlight(flightId, newNumber, newDeparture, newArrival, newDuration);
        List<Flight> flights = flightService.findAll();
        model.addAttribute("flights", flights);
        return "redirect:/flight";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/flight_delete_by_id")
    public String deleteById(@RequestParam Long flightId, Model model ) {
        flightService.deleteById(flightId);
        List<Flight> flights = flightService.findAll();
        model.addAttribute("flights", flights);
        return "redirect:/flight";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/flight_delete_all")
    public String deleteAll(Model model) {
        flightService.deleteAll();
        List<Flight> flights = flightService.findAll();
        model.addAttribute("flights", flights);
        return "redirect:/flight";
    }

}
