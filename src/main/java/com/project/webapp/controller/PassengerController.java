package com.project.webapp.controller;

import com.project.webapp.model.Passenger;
import com.project.webapp.service.PassengerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class PassengerController {

    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping("/passenger_airplane_page")
    public String getAirplanePage() {
        return "redirect:/airplane";
    }

    @GetMapping("/passenger_flight_page")
    public String getFlightPage() {
        return "redirect:/flight";
    }

    @GetMapping("/passenger_ticket_page")
    public String getTicketPage() {
        return "redirect:/ticket";
    }

    @GetMapping("/passenger/find")
    public Optional<Passenger> findByEmail(@RequestParam String email) {
        return passengerService.findByEmail(email);
    }

    @GetMapping("/passenger")
    public String findAllPassengers(Model model) {
        List<Passenger> passengers = passengerService.findAll();
        model.addAttribute("passengers", passengers);
        return "passengers";
    }

    @PostMapping("/passenger")
    public String addPassenger(@RequestParam String firstName,
                                  @RequestParam String lastName,
                                  @RequestParam String email,
                                  @RequestParam String phoneNumber,
                                  Model model)
    {
        Passenger passenger = new Passenger();
        passenger.setFirstName(firstName);
        passenger.setLastName(lastName);
        passenger.setEmail(email);
        passenger.setPhoneNumber(phoneNumber);
        passengerService.addPassenger(passenger);

        List<Passenger> passengers = passengerService.findAll();
        model.addAttribute("passengers", passengers);
        return "passengers";
    }

    @PostMapping("/passenger_delete_all")
    public String deleteAll(Model model) {
        passengerService.deleteAll();
        List<Passenger> passengers = passengerService.findAll();
        model.addAttribute("passengers", passengers);
        return "passengers";
    }
}
