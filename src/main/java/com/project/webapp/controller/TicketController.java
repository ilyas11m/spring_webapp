package com.project.webapp.controller;

import com.project.webapp.model.Flight;
import com.project.webapp.model.Passenger;
import com.project.webapp.model.Ticket;
import com.project.webapp.service.FlightService;
import com.project.webapp.service.PassengerService;
import com.project.webapp.service.TicketService;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/ticket_airplane_page")
    public String getAirplanePage() {
        return "redirect:/airplane";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/ticket_flight_page")
    public String getFlightPage() {
        return "redirect:/flight";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/ticket_passenger_page")
    public String getPassengerPage() {
        return "redirect:/passenger";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/ticket")
    public String findAll(Model model) {
        List<Ticket> tickets = ticketService.findAll();
        model.addAttribute("tickets", tickets);
        return "tickets";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/ticket_find")
    public String findTicket(@RequestParam(required = false) String ticketNumber,
                             @RequestParam(required = false) String seatNumber,
                             @RequestParam(required = false) BigDecimal price,
                             Model model) {
        List<Ticket> foundTickets = ticketService.findAll();

        if (ticketNumber != null && !ticketNumber.isEmpty()) {
            foundTickets = foundTickets.stream()
                    .filter(ticket -> ticket.getTicketNumber().equalsIgnoreCase(ticketNumber))
                    .toList();
        }
        if (seatNumber != null && !seatNumber.isEmpty()) {
            foundTickets = foundTickets.stream()
                    .filter(ticket -> ticket.getSeatNumber().equalsIgnoreCase(seatNumber))
                    .toList();
        }
        if (price != null) {
            foundTickets = foundTickets.stream()
                    .filter(ticket -> ticket.getPrice().compareTo(price) == 0)
                    .toList();
        }

        model.addAttribute("tickets", foundTickets);
        return "tickets";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
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

        return "redirect:/ticket";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/ticket_edit")
    public String editTicket (@RequestParam Long ticketId,
                              @RequestParam(required = false) String newTicketNumber,
                              @RequestParam(required = false) String newSeatNumber,
                              @RequestParam(required = false) BigDecimal newPrice,
                              Model model) {
        if ((newTicketNumber == null || newTicketNumber.isEmpty()) && (newSeatNumber == null || newSeatNumber.isEmpty())
                && newPrice == null) {
            throw new IllegalArgumentException("At least one field must be provided for update.");
        }
        ticketService.updateTicket(ticketId, newTicketNumber, newSeatNumber, newPrice);
        List<Ticket> tickets = ticketService.findAll();
        model.addAttribute("tickets", tickets);
        return "redirect:/ticket";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/ticket_delete_by_id")
    public String deleteById(Long ticketId, Model model) {
        ticketService.deleteById(ticketId);
        List<Ticket> tickets = ticketService.findAll();
        model.addAttribute("tickets", tickets);
        return "redirect:/ticket";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/ticket_delete_all")
    public String deleteAll(Model model) {
        ticketService.deleteAll();
        List<Ticket> tickets = ticketService.findAll();
        model.addAttribute("tickets", tickets);
        return "redirect:/ticket";
    }
}
