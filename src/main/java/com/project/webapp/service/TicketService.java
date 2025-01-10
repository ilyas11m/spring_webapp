package com.project.webapp.service;

import com.project.webapp.model.Ticket;
import com.project.webapp.repository.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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

    public void deleteAll() {
        ticketRepository.deleteAll();
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

}
