package com.project.webapp.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "airplanes")
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airplane_id")
    private Long airplaneId;

    @OneToMany(mappedBy = "airplane", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Flight> flights = new ArrayList<>();

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @Column(name = "distance", nullable = false)
    private Integer distance;

    public long getAirplaneId() {
        return airplaneId;
    }

    public void setAirplaneId(Long airplaneId) {
        this.airplaneId = airplaneId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return model;
    }
}
