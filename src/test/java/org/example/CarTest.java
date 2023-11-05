package org.example;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CarTest {
    private Car car;

    @BeforeClass
    public void setUp() {
        // Create a Car instance for testing
        car = new Car.Builder("Nissan", "GT-R", 1800.0)
                .setNumberOfDoors(2)
                .setElectric(false)
                .setProductionYear(2022)
                .setPrice(25000.0) // Invalid price
                .build();
    }

    @Test
    public void testGetNumberOfDoors() {
        int numberOfDoors = car.getNumberOfDoors();
        Assert.assertEquals(numberOfDoors, 2, "Number of doors should be 2");
    }

    @Test
    public void testIsElectric() {
        boolean electric = car.isElectric();
        Assert.assertFalse(electric, "The car should not be electric");
    }

    @Test
    public void testGetBrand() {
        String brand = car.getBrand();
        Assert.assertEquals(brand, "Nissan", "Brand should be 'Nissan'");
    }

    @Test
    public void testGetModel() {
        String model = car.getModel();
        Assert.assertEquals(model, "GT-R", "Model should be 'GT-R'");
    }

    @Test
    public void testGetWeight() {
        double weight = car.getWeight();
        Assert.assertEquals(weight, 1800.0, "Type should be 'Coupe'");
    }

    @Test
    public void testGetProductionYear() {
        int productionYear = car.getProductionYear();
        Assert.assertEquals(productionYear, 2022, "Production year should be 2022");
    }

    @Test
    public void testGetPrice() {
        double price = car.getPrice();
        Assert.assertEquals(price, 25000.0, "Price should be 25000.0");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testWithNegativeValue() {
        new Car.Builder("Toyota", "Camry", 2200.0)
                .setNumberOfDoors(4)
                .setElectric(false)
                .setProductionYear(2022)
                .setPrice(-25000.0) // Invalid price
                .build();
    }
}

