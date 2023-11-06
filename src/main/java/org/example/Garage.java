package org.example;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Garage {
    private final Map<Car, LocalDate> cars;
    @Min(value = 0, message = "Мінімальна кількість транспорту повинна бути >= 0")
    @Max(value = 100, message = "Мінімальна кількість транспорту не повинна перевищувати 100")
    private int capacity;

    public static class Builder {
        private int capacity;

        public Builder withCapacity(@Min(0) @Max(100) int capacity) {
            this.capacity = capacity;
            return this;
        }

        public Garage build() {
            Garage garage = new Garage(this.capacity);

            if (capacity < 0 || capacity > 100) {
                throw new IllegalArgumentException("Invalid capacity value.");
            }

            return garage;
        }
    }

    public Garage(int capacity) {
        cars = new HashMap<>();
        this.capacity = capacity;
    }

    public boolean isFull() {
        return cars.size() == capacity;
    }

    public void addCar(Car car) {
        addCar(car, LocalDate.now());
    }

    public void addCar(Car car, LocalDate date) {
        if (isFull()) {
            throw new IllegalArgumentException("The garage is full. Cannot add more cars.");
        }

        cars.put(car, date);
    }

    public void removeCar(Car car) {
        if (!cars.containsKey(car)) {
            throw new IllegalArgumentException("Car not found in the garage.");
        }

        cars.remove(car);
    }

    public void setCapacity(int newCapacity) {
        if (newCapacity < cars.size()) {
            throw new IllegalArgumentException("New capacity is less than the current number of cars.");
        }
        capacity = newCapacity;
    }

    public int getAvailableSpots() {
        return capacity - cars.size();
    }

    public List<Car> getAllCars() {
        return new ArrayList<>(cars.keySet());
    }

    public Map<Car, LocalDate> getCars() {
        return cars;
    }

}
