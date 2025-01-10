package com.project.webapp.repository;

import com.project.webapp.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
