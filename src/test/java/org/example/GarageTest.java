package org.example;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class GarageTest {
    private Garage garage;

    @BeforeMethod
    public void setUp() {
        // Initialize a new garage with a capacity of 2 cars
        garage = new Garage(2);
    }

    @Test
    public void testAddCar() {
        // Create cars
        Car car1 = new Car.Builder("Toyota", "Camry", 2000)
                .setNumberOfDoors(4)
                .setElectric(false)
                .build();
        Car car2 = new Car.Builder("Tesla", "Model S", 2022)
                .setNumberOfDoors(4)
                .setElectric(true)
                .build();

        // Add cars to the garage
        garage.addCar(car1);
        garage.addCar(car2);

        // Check if the cars were added successfully
        Map<Car, LocalDate> carsInfo = garage.getCars();
        Assert.assertEquals(carsInfo.size(), 2);
    }

    @Test
    public void testRemoveCar() {
        // Create a car
        Car car = new Car.Builder("Toyota", "Corolla", 2019)
                .setNumberOfDoors(4)
                .setElectric(false)
                .build();

        // Add the car to the garage
        garage.addCar(car);

        // Remove the car from the garage
        garage.removeCar(car);

        // Check if the car was removed successfully
        Map<Car, LocalDate> carsInfo = garage.getCars();
        Assert.assertEquals(carsInfo.size(), 0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddCarWhenGarageIsFull() {
        // Create cars
        Car car1 = new Car.Builder("Ford", "Focus", 2020)
                .setNumberOfDoors(4)
                .setElectric(false)
                .build();
        Car car2 = new Car.Builder("Honda", "Civic", 2021)
                .setNumberOfDoors(4).
                setElectric(false)
                .build();
        Car car3 = new Car.Builder("Chevrolet", "Malibu", 2019)
                .setNumberOfDoors(4)
                .setElectric(false)
                .build();

        // Add cars to the garage (garage capacity is 2, so the third car should throw an exception)
        garage.addCar(car1);
        garage.addCar(car2);
        garage.addCar(car3);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testRemoveNonexistentCar() {
        // Create a car
        Car car = new Car.Builder("Nissan", "Altima", 2017)
                .setNumberOfDoors(4)
                .setElectric(false)
                .build();

        // Try to remove a car that doesn't exist in the garage
        garage.removeCar(car);
    }
}
