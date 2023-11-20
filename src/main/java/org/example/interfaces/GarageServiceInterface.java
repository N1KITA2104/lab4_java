package org.example.interfaces;

import org.example.Car;

import java.time.LocalDate;
import java.util.List;

public interface GarageServiceInterface {

    // Default
    List<Car> findCarsByBrand(String brand);

    List<Car> findCarsByModel(String model);

    List<Car> findCarsByProductionYear(int productionYear);

    List<Car> getCarsAddedBetween(LocalDate startDate, LocalDate endDate);

    // Stream API
    List<Car> findCarsByBrandStream(String brand);

    List<Car> findCarsByModelStream(String model);

    List<Car> findCarsByProductionYearStream(int productionYear);

    List<Car> getCarsAddedBetweenStream(LocalDate startDate, LocalDate endDate);
}

