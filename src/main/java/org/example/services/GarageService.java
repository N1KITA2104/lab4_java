package org.example.services;

import org.example.Car;
import org.example.Garage;
import org.example.interfaces.GarageServiceInterface;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class GarageService implements GarageServiceInterface {
    private final Garage garage;

    public GarageService(Garage garage) {
        this.garage = garage;
    }

    @Override
    public List<Car> findCarsByBrand(String brand) {
        List<Car> foundCars = new ArrayList<>();
        for (Car car : garage.getCars().keySet()) {
            if (car.getBrand().equalsIgnoreCase(brand)) {
                foundCars.add(car);
            }
        }
        foundCars.sort(new BrandComparator()); // Сортування за брендом
        return foundCars;
    }

    @Override
    public List<Car> findCarsByModel(String model) {
        List<Car> foundCars = new ArrayList<>();
        for (Car car : garage.getCars().keySet()) {
            if (car.getModel().equalsIgnoreCase(model)) {
                foundCars.add(car);
            }
        }
        foundCars.sort(Comparator.comparing(Car::getModel)); // Сортування за моделлю
        return foundCars;
    }

    @Override
    public List<Car> findCarsByProductionYear(int productionYear) {
        List<Car> foundCars = new ArrayList<>();
        for (Car car : garage.getCars().keySet()) {
            if (car.getProductionYear() == productionYear) {
                foundCars.add(car);
            }
        }
        foundCars.sort(Comparator.comparingInt(Car::getProductionYear)); // Сортування за роком виробництва
        return foundCars;
    }

    @Override
    public List<Car> getCarsAddedBetween(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date.");
        }

        return garage.getCars().entrySet().stream()
                .filter(entry -> {
                    LocalDate carAddDate = entry.getValue();
                    return carAddDate.isEqual(startDate) || (carAddDate.isAfter(startDate) && carAddDate.isBefore(endDate));
                })
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private static class BrandComparator implements Comparator<Car> {
        @Override
        public int compare(Car car1, Car car2) {
            return car1.getBrand().compareTo(car2.getBrand());
        }
    }
}
