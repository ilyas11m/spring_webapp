package com.project.webapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private Long flightId;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();

    @Column(name = "flight_number", unique = true, nullable = false)
    private String flightNumber;

    @Column(name = "departure_location", nullable = false)
    private String departureLocation;

    @Column(name = "arrival_location", nullable = false)
    private String arrivalLocation;

    @Column(name = "duration_minutes", nullable = false)
    private Integer durationMinutes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airplane_id", nullable = false)
    private Airplane airplane;

    @Override
    public String toString() {
        return "Номер полета = " + flightNumber + " . " + departureLocation +
                " - " + arrivalLocation + " . Самолёт - " + airplane;
    }
}
