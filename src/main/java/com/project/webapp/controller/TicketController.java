package com.project.webapp.controller;

import com.project.webapp.model.Flight;
import com.project.webapp.model.Passenger;
import com.project.webapp.model.Ticket;
import com.project.webapp.service.FlightService;
import com.project.webapp.service.PassengerService;
import com.project.webapp.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class TicketController {

    private final TicketService ticketService;
    private final FlightService flightService;
    private final PassengerService passengerService;

    public TicketController(TicketService ticketService, FlightService flightService, PassengerService passengerService) {
        this.ticketService = ticketService;
        this.flightService = flightService;
        this.passengerService = passengerService;
    }

    @GetMapping("/ticket_airplane_page")
    public String getAirplanePage() {
        return "redirect:/airplane";
    }

    @GetMapping("/ticket_flight_page")
    public String getFlightPage() {
        return "redirect:/flight";
    }

    @GetMapping("/ticket_passenger_page")
    public String getPassengerPage() {
        return "redirect:/passenger";
    }

    @GetMapping("/ticket")
    public String findAll(Model model) {
        List<Ticket> tickets = ticketService.findAll();
        model.addAttribute("tickets", tickets);
        return "tickets";
    }

    @PostMapping("/ticket")
    public String addTicket(@RequestParam String ticketNumber, @RequestParam String seatNumber,
                            @RequestParam BigDecimal price, @RequestParam Long passengerId,
                            @RequestParam Long flightId, Model model)
    {
        Flight flight = flightService.findById(flightId);
        Passenger passenger = passengerService.findById(passengerId);
        Ticket ticket = new Ticket();
        ticket.setTicketNumber(ticketNumber);
        ticket.setSeatNumber(seatNumber);
        ticket.setPrice(price);

        ticket.setFlight(flight);
        ticket.setPassenger(passenger);

        ticketService.addTicket(ticket);

        List<Ticket> tickets = ticketService.findAll();
        model.addAttribute("tickets", tickets);

        return "tickets";
    }

    @PostMapping("/ticket_delete_all")
    public String deleteAll(Model model) {
        ticketService.deleteAll();
        List<Ticket> tickets = ticketService.findAll();
        model.addAttribute("tickets", tickets);
        return "tickets";
    }

}
