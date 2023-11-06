package org.example;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CarTest {
    private Car car;


    @Test
    public void testGetNumberOfDoors() {
        car = new Car.Builder("Nissan", "GT-R", 1800.0)
                .setNumberOfDoors(2)
                .setElectric(false)
                .setProductionYear(2022)
                .setPrice(25000.0)
                .build();
        int numberOfDoors = car.getNumberOfDoors();
        assertEquals(numberOfDoors, 2, "Number of doors should be 2");
    }

    @Test
    public void testIsElectric() {
        car = new Car.Builder("Nissan", "GT-R", 1800.0)
                .setNumberOfDoors(2)
                .setElectric(false)
                .setProductionYear(2022)
                .setPrice(25000.0)
                .build();
        boolean electric = car.isElectric();
        Assert.assertFalse(electric, "The car should not be electric");
    }

    @Test
    public void testGetBrand() {
        car = new Car.Builder("Nissan", "GT-R", 1800.0)
                .setNumberOfDoors(2)
                .setElectric(false)
                .setProductionYear(2022)
                .setPrice(25000.0)
                .build();
        String brand = car.getBrand();
        assertEquals(brand, "Nissan", "Brand should be 'Nissan'");
    }

    @Test
    public void testGetModel() {
        car = new Car.Builder("Nissan", "GT-R", 1800.0)
                .setNumberOfDoors(2)
                .setElectric(false)
                .setProductionYear(2022)
                .setPrice(25000.0)
                .build();
        String model = car.getModel();
        assertEquals(model, "GT-R", "Model should be 'GT-R'");
    }

    @Test
    public void testGetWeight() {
        car = new Car.Builder("Nissan", "GT-R", 1800.0)
                .setNumberOfDoors(2)
                .setElectric(false)
                .setProductionYear(2022)
                .setPrice(25000.0)
                .build();
        double weight = car.getWeight();
        assertEquals(weight, 1800.0, "Type should be 'Coupe'");
    }

    @Test
    public void testGetProductionYear() {
        car = new Car.Builder("Nissan", "GT-R", 1800.0)
                .setNumberOfDoors(2)
                .setElectric(false)
                .setProductionYear(2022)
                .setPrice(25000.0)
                .build();
        int productionYear = car.getProductionYear();
        assertEquals(productionYear, 2022, "Production year should be 2022");
    }

    @Test
    public void testGetPrice() {
        car = new Car.Builder("Nissan", "GT-R", 1800.0)
                .setNumberOfDoors(2)
                .setElectric(false)
                .setProductionYear(2022)
                .setPrice(25000.0)
                .build();
        double price = car.getPrice();
        assertEquals(price, 25000.0, "Price should be 25000.0");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
//    @Test
    public void testWithNegativeValue() {
        Car car = new Car.Builder("Toyota", "Camry", 2200.0)
                .setNumberOfDoors(0)
                .setElectric(false)
                .setProductionYear(-2022)
                .setPrice(-25000.0) // Invalid price
                .build();
//        assertEquals(car.getPrice(), -25000);
    }
}

