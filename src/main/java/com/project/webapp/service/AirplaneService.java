package com.project.webapp.service;

import com.project.webapp.model.Airplane;
import com.project.webapp.repository.AirplaneRepository;
import com.project.webapp.repository.FlightRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirplaneService {

    private final AirplaneRepository airplaneRepository;

    public AirplaneService(AirplaneRepository airplaneRepository, FlightRepository flightRepository) {
        this.airplaneRepository = airplaneRepository;
    }
    public void addAirplane(Airplane airplane) {
        airplaneRepository.save(airplane);
    }

    public Airplane findById(Long airplaneId) {
        return airplaneRepository.findById(airplaneId).orElse(null);
    }

    public List<Airplane> findAll() {
        return airplaneRepository.findAll();
    }

    @Transactional
    public Airplane updateAirplane(Long id, String newModel, Integer newCapacity, Integer newDistance) {
        Airplane airplane = airplaneRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Airplane with ID " + id + " not found"));

        if (newModel != null && !newModel.isEmpty()) {
            airplane.setModel(newModel);
        }
        if (newCapacity != null && newCapacity > 0) {
            airplane.setCapacity(newCapacity);
        }
        if (newDistance != null && newDistance > 0) {
            airplane.setDistance(newDistance);
        }
        return airplaneRepository.save(airplane);
    }

    @Transactional
    public void deleteById (Long id) {
        airplaneRepository.deleteById(id);
    }

    public void deleteAll() {
        airplaneRepository.deleteAll();
    }
}
