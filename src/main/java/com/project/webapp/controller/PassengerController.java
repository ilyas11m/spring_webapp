package com.project.webapp.controller;

import com.project.webapp.model.Passenger;
import com.project.webapp.service.PassengerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class PassengerController {

    /**/private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/passenger_airplane_page")
    public String getAirplanePage() {
        return "redirect:/airplane";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/passenger_flight_page")
    public String getFlightPage() {
        return "redirect:/flight";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/passenger_ticket_page")
    public String getTicketPage() {
        return "redirect:/ticket";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/passenger")
    public String findAllPassengers(Model model) {
        List<Passenger> passengers = passengerService.findAll();
        model.addAttribute("passengers", passengers);
        return "passengers";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/passenger_back")
    public String back() {
        return "redirect:/passenger";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/passenger_find")
    public String find(@RequestParam(required = false) String firstName,
                       @RequestParam(required = false) String lastName,
                       @RequestParam(required = false) String email,
                       @RequestParam(required = false) String phoneNumber,
                       Model model) {
        List<Passenger> foundPassengers = passengerService.findAll();
        if (firstName != null && !firstName.isEmpty()) {
            foundPassengers = foundPassengers.stream()
                    .filter(passenger -> passenger.getFirstName().equalsIgnoreCase(firstName))
                    .toList();
        }
        if (lastName != null && !lastName.isEmpty()) {
            foundPassengers = foundPassengers.stream()
                    .filter(passenger -> passenger.getLastName().equalsIgnoreCase(lastName))
                    .toList();
        }
        if (email != null && !email.isEmpty()) {
            foundPassengers = foundPassengers.stream()
                    .filter(passenger -> passenger.getEmail().equalsIgnoreCase(email))
                    .toList();
        }
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            foundPassengers = foundPassengers.stream()
                    .filter(passenger -> passenger.getPhoneNumber().equalsIgnoreCase(phoneNumber))
                    .toList();
        }
        model.addAttribute("passengers", foundPassengers);
        return "passengers";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
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

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/passenger_edit")
    public String editPassenger (@RequestParam Long passengerId,
                                 @RequestParam(required = false) String newFirstName,
                                 @RequestParam(required = false) String newLastName,
                                 @RequestParam(required = false) String newEmail,
                                 @RequestParam(required = false) String newPhoneNumber,
                                 Model model) {
        if ((newFirstName == null || newFirstName.isEmpty()) && (newLastName == null || newLastName.isEmpty())
                && (newEmail == null || newEmail.isEmpty()) && (newPhoneNumber == null || newPhoneNumber.isEmpty())) {
            throw new IllegalArgumentException("At least one field must be provided for update.");
        }
        passengerService.updatePassenger(passengerId, newFirstName, newLastName, newEmail, newPhoneNumber);
        List<Passenger> passengers = passengerService.findAll();
        model.addAttribute("passengers", passengers);
        return "redirect:/passenger";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/passenger_delete_by_id")
    public String deleteById(@RequestParam Long passengerId, Model model) {
        passengerService.deleteById(passengerId);
        List<Passenger> passengers = passengerService.findAll();
        model.addAttribute("passengers", passengers);
        return "redirect:/passenger";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/passenger_delete_all")
    public String deleteAll(Model model) {
        passengerService.deleteAll();
        List<Passenger> passengers = passengerService.findAll();
        model.addAttribute("passengers", passengers);
        return "redirect:/passenger";
    }
}
