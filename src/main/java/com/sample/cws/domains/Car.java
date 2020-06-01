package com.sample.cws.domains;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Car {

    @JsonProperty("location")
    private String carLocation;

    private List<Vehicle> vehicles = new ArrayList<>();

    public Car() {
    }

    public Car(String carLocation, List<Vehicle> vehicles) {
        this.carLocation = carLocation;
        this.vehicles = vehicles;
    }

    public String getCarLocation() {
        return carLocation;
    }

    public void setCarLocation(String carLocation) {
        this.carLocation = carLocation;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carLocation='" + carLocation + '\'' +
                ", vehicles=" + vehicles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (!carLocation.equals(car.carLocation)) return false;
        return vehicles != null ? vehicles.equals(car.vehicles) : car.vehicles == null;
    }

    @Override
    public int hashCode() {
        int result = carLocation.hashCode();
        result = 31 * result + (vehicles != null ? vehicles.hashCode() : 0);
        return result;
    }

    public boolean isVehicleExist(long vehicleId){
        Optional<Vehicle> vehicleOptional = getVehicles().stream().filter(v -> v.getId() == vehicleId).findFirst();
        return vehicleOptional.isPresent();
    }

    public Vehicle getVehicleById(long vehicleId) {
        Optional<Vehicle> vehicleOptional = getVehicles().stream().filter(v -> v.getId() == vehicleId).findFirst();
        return vehicleOptional.get();
    }
}
