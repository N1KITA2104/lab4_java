package org.example;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;


public class GarageServiceTest {
    private GarageServiceInterface garageService;

    @BeforeMethod
    public void beforeMethod() {
        Garage garage = new Garage(10);
        garageService = new GarageService(garage); // Використовуємо інтерфейс

        // Створення автомобілів і додавання їх в гараж
        Car car1 = new Car.Builder("Nissan", "GT-R", 2000)
                .setProductionYear(2018)
                .setPrice(95000)
                .build();
        Car car2 = new Car.Builder("Nissan", "Skyline", 2200)
                .setProductionYear(2018)
                .setPrice(120000)
                .build();
        Car car3 = new Car.Builder("Ferrari", "Spider", 1400)
                .setProductionYear(2012)
                .setPrice(235000)
                .build();
        Car car4 = new Car.Builder("Lamborghini", "Aventador", 1800)
                .setProductionYear(2023)
                .setPrice(1350000)
                .build();

        garage.addCar(car1, LocalDate.of(2022, 1, 1));
        garage.addCar(car2);
        garage.addCar(car3);
        garage.addCar(car4);
    }

    @Test
    public void testFindCarsByBrand() {
        List<Car> foundCarsByBrand = garageService.findCarsByBrand("Nissan");
        Assert.assertEquals(foundCarsByBrand.size(), 2);
    }

    @Test
    public void testFindCarsByModel() {
        List<Car> foundCarsByModel = garageService.findCarsByModel("GT-R");
        Assert.assertEquals(foundCarsByModel.size(), 1);
    }

    @Test
    public void testFindCarsByProductionYear() {
        List<Car> foundCarsByProductionYear = garageService.findCarsByProductionYear(2018);
        Assert.assertEquals(foundCarsByProductionYear.size(), 2);
    }

    @Test
    public void testGetCarsAddedBetween() {
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);
        List<Car> foundCarsAddedBetween = garageService.getCarsAddedBetween(startDate, endDate);
        Assert.assertEquals(foundCarsAddedBetween.size(), 3);
    }
}