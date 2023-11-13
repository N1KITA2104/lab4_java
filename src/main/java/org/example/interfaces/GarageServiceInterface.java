package org.example.interfaces;

import org.example.Car;

import java.time.LocalDate;
import java.util.List;

public interface GarageServiceInterface {

    List<Car> findCarsByBrand(String brand);

    List<Car> findCarsByModel(String model);

    List<Car> findCarsByProductionYear(int productionYear);

    List<Car> getCarsAddedBetween(LocalDate startDate, LocalDate endDate);
}

