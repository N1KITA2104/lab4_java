package org.example;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TxtSerializationFormatTest {
    private TxtSerializationFormat<Transport> serializer;
    private final String testFilePath = "test_data.txt";

    @BeforeMethod
    public void setUp() {
        serializer = new TxtSerializationFormat<>();
    }

    @AfterMethod
    public void tearDown() {
        File testFile = new File(testFilePath);
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    public void testWriteAndReadFromFile() throws IOException {
        List<Transport> transportList = new ArrayList<>();
        transportList.add(new Transport.Builder("Toyota", "Supra", 1000.0)
                .setProductionYear(2020)
                .setPrice(50000.0)
                .build());
        transportList.add(new Transport.Builder("Nissan", "GT-R", 1500.0)
                .setProductionYear(2022)
                .setPrice(160000.0)
                .build());

        serializer.writeToFile(transportList, testFilePath);

        List<Transport> deserializedList = serializer.readFromFile(testFilePath);

        Assert.assertEquals(((List<?>) deserializedList).size(), transportList.size());
        for (int i = 0; i < transportList.size(); i++) {
            Transport original = transportList.get(i);
            Transport deserialized = deserializedList.get(i);
            Assert.assertEquals(original.getBrand(), deserialized.getBrand());
            Assert.assertEquals(original.getModel(), deserialized.getModel());
            Assert.assertEquals(original.getWeight(), deserialized.getWeight(), 0.001);
            Assert.assertEquals(original.getProductionYear(), deserialized.getProductionYear());
            Assert.assertEquals(original.getPrice(), deserialized.getPrice(), 0.001);
        }
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeserializeInvalidDataThrowsException() throws IOException {
        TxtSerializationFormat<Transport> txtSerializationFormat = new TxtSerializationFormat<>();

        // Подготавливаем неверные данные для десериализации
        String invalidData = "Brand: InvalidBrand\n" +
                "Model: ValidModel\n" +
                "Transport Weight: 1000.0\n" +
                "Production Year: -2020\n" +  // Неверный формат для year
                "Price: 5000.0";

        // Десериализация вызовет IllegalArgumentException
        txtSerializationFormat.deserialize(invalidData);
    }
}
