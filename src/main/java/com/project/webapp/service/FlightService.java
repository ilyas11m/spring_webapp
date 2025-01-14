package com.project.webapp.service;

import com.project.webapp.model.Flight;
import com.project.webapp.repository.AirplaneRepository;
import com.project.webapp.repository.FlightRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository, AirplaneRepository airplaneRepository) {
        this.flightRepository = flightRepository;
    }

    @Transactional
    public void addFlight(Flight flight) {
        flightRepository.save(flight);
    }

    @Transactional
    public Flight updateFlight(Long flightId,
                               String newNumber,
                               String newDeparture,
                               String newArrival,
                               Integer newDuration) {
        Flight flight = flightRepository.findById(flightId).orElseThrow(
                ()-> new IllegalArgumentException("Flight with ID" + flightId + "is not found!"));

        if (newNumber != null && !newNumber.isEmpty()) {
            flight.setFlightNumber(newNumber);
        }
        if (newDeparture != null && !newDeparture.isEmpty()) {
            flight.setDepartureLocation(newDeparture);
        }
        if (newArrival != null && !newArrival.isEmpty()) {
            flight.setArrivalLocation(newArrival);
        }
        if (newDuration != null && newDuration > 0) {
            flight.setDurationMinutes(newDuration);
        }
        return flightRepository.save(flight);
    }

    public Flight findById(Long flightId) {
        return flightRepository.findById(flightId).orElse(null);
    }

    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id) {
        flightRepository.deleteById(id);
    }

    public void deleteAll() {
        flightRepository.deleteAll();
    }
}
