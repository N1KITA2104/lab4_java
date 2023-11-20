package org.example.services;

import org.example.Car;
import org.example.Garage;
import org.example.interfaces.GarageServiceInterface;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
        foundCars.sort(new ModelComparator()); // Сортування за брендом
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
        foundCars.sort(Comparator.comparing(Car::getProductionYear)); // Сортування за моделлю
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

        List<Car> carsAddedBetween = new ArrayList<>();

        for (Map.Entry<Car, LocalDate> entry : garage.getCars().entrySet()) {
            LocalDate carAddDate = entry.getValue();
            if (carAddDate.isEqual(startDate) || (carAddDate.isAfter(startDate) && carAddDate.isBefore(endDate))) {
                carsAddedBetween.add(entry.getKey());
            }
        }

        return carsAddedBetween;
    }

    @Override
    public List<Car> findCarsByBrandStream(String brand) {
        return garage.getCars().keySet().stream()
                .filter(car -> car.getBrand().equalsIgnoreCase(brand))
                .sorted(Comparator.comparing(Car::getBrand))
                .collect(Collectors.toList());
    }

    @Override
    public List<Car> findCarsByModelStream(String model) {
        return garage.getCars().keySet().stream()
                .filter(car -> car.getModel().equalsIgnoreCase(model))
                .sorted(Comparator.comparing(Car::getModel))
                .collect(Collectors.toList());
    }

    @Override
    public List<Car> findCarsByProductionYearStream(int productionYear) {
        return garage.getCars().keySet().stream()
                .filter(car -> car.getProductionYear() == productionYear)
                .sorted(Comparator.comparingInt(Car::getProductionYear))
                .collect(Collectors.toList());
    }


    @Override
    public List<Car> getCarsAddedBetweenStream(LocalDate startDate, LocalDate endDate) {
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

    private static class ModelComparator implements Comparator<Car> {
        @Override
        public int compare(Car car1, Car car2) {
            return car1.getModel().compareTo(car2.getModel());
        }
    }
}
