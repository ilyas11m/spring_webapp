package com.project.webapp.service;

import com.project.webapp.model.Ticket;
import com.project.webapp.repository.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Transactional
    public Ticket addTicket (Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket findById (Long id) {
        return ticketRepository.findById(id).orElse(null);
    }

    public Ticket updateTicket (Long id, String newTicketNumber, String newSeatNumber, BigDecimal newPrice) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Ticket with ID " + id + " not found"));
        if (newTicketNumber != null && !newTicketNumber.isEmpty()) {
            ticket.setTicketNumber(newTicketNumber);
        }
        if (newSeatNumber != null && !newSeatNumber.isEmpty()) {
            ticket.setSeatNumber(newSeatNumber);
        }
        if (newPrice != null && newPrice.compareTo(BigDecimal.ZERO) > 0) {
            ticket.setPrice(newPrice);
        }

        return ticketRepository.save(ticket);
    }

    public void deleteById (Long id) {
        ticketRepository.deleteById(id);
    }

    public void deleteAll() {
        ticketRepository.deleteAll();
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

}
