package com.project.webapp.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table (name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "ticket_id")
    private Long ticketId;

    @Column(name = "ticket_number", nullable = false, unique = true)
    private String ticketNumber;

    @Column(name = "seat_number", nullable = false)
    private String seatNumber;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id", nullable = false)
    private Passenger passenger;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
}
