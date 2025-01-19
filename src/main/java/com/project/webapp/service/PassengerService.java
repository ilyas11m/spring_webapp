package com.project.webapp.service;

import com.project.webapp.model.Passenger;
import com.project.webapp.repository.PassengerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Transactional
    public Passenger updatePassenger(Long id, String firstName, String lastName, String email, String phoneNumber) {
        Passenger passenger = passengerRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Passenger" +
                " with ID " + id + " not found"));
        if (firstName != null && !firstName.isEmpty()) {
            passenger.setFirstName(firstName);
        }
        if (lastName != null && !lastName.isEmpty()) {
            passenger.setLastName(lastName);
        }
        if (email != null && !email.isEmpty()) {
            passenger.setEmail(email);
        }
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            passenger.setPhoneNumber(phoneNumber);
        }
        return passengerRepository.save(passenger);
    }

    public void deleteById(Long id) {
        passengerRepository.deleteById(id);
    }

    public void deleteAll() {
        passengerRepository.deleteAll();
    }
}
