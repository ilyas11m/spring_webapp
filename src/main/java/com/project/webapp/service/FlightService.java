package com.project.webapp.service;

import com.project.webapp.model.Flight;
import com.project.webapp.repository.AirplaneRepository;
import com.project.webapp.repository.FlightRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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

    public void deleteAll() {
        flightRepository.deleteAll();
    }

    public void deleteById(Long id) {
        flightRepository.deleteById(id);
    }

    public Flight findById(Long flightId) {
        return flightRepository.findById(flightId).orElse(null);
    }

    public List<Flight> findAll() {
        return flightRepository.findAll();
    }
}
