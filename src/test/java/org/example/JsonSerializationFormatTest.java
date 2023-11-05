package org.example;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonSerializationFormatTest {
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSerializationInvalidObject() {
        // Создаем объект Transport для тестирования
        Transport transport = new Transport.Builder("Nissan", "GT-R", 2000.0)
                .setProductionYear(2024)
                .setPrice(-120000.0)
                .build();
        String serializedJson = jsonSerializer.serialize(transport);
        // Если код дошел до этой точки, то тест не прошел
    }

    private final JsonSerializationFormat<Transport> jsonSerializer = new JsonSerializationFormat<>(Transport.class);

    @Test
    public void testSerializationDeserialization() {
        // Создаем объект Transport для тестирования
        Transport transport = new Transport.Builder("Nissan", "GT-R", 2000.0)
                .setProductionYear(2024)
                .setPrice(120000.0)
                .build();

        // Сериализуем объект в JSON
        String serializedJson = jsonSerializer.serialize(transport);

        // Десериализуем JSON в объект
        Transport deserializedTransport = jsonSerializer.deserialize(serializedJson);

        // Проверяем, что объекты совпадают
        Assert.assertEquals(deserializedTransport, transport);
    }

    @Test
    public void testWriteReadToFile() throws IOException {
        // Create a list of imaginary car objects for serialization
        List<Transport> cars = new ArrayList<>();

        // Adding sample car objects
        cars.add(new Transport.Builder("Nissan", "GT-R", 2000.0)
                .setProductionYear(2024)
                .setPrice(120000.0)
                .build());
        cars.add(new Transport.Builder("Tesla", "Model 3", 2000.0)
                .setProductionYear(2023)
                .setPrice(70000.0)
                .build());
        cars.add(new Transport.Builder("Ford", "Mustang", 2000.0)
                .setProductionYear(2016)
                .setPrice(25000.0)
                .build());

        // Specify the path to the temporary JSON file
        String filePath = "cars.json";

        // Create an instance of JsonSerializationFormat for Car objects
        JsonSerializationFormat<Transport> jsonFormat = new JsonSerializationFormat<>(Transport.class);

        // Write the list of car objects to the file
        jsonFormat.writeToFile(cars, filePath);

        // Read from the file and deserialize
        List<Transport> readCars = jsonFormat.readFromFile(filePath);

        // Compare the original list and the list read from the file
        Assert.assertEquals(cars, readCars);
    }
}