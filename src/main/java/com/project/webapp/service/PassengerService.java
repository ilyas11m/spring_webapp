package com.project.webapp.service;

import com.project.webapp.model.Passenger;
import com.project.webapp.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {

    private final PassengerRepository passengerRepository;

    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public void addPassenger(Passenger passenger) {
        passengerRepository.save(passenger);
    }

    public List<Passenger> findAll() {
        return passengerRepository.findAll();
    }

    public Passenger findById(Long passengerId) {
        return passengerRepository.findById(passengerId).orElse(null);
    }

    public Optional<Passenger> findByEmail(String email) {
        return passengerRepository.findByEmail(email);
    }

    public void deleteAll() {
        passengerRepository.deleteAll();
    }
}
