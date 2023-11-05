package org.example;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlSerializationFormatTest {
    @Test
    public void testSerializationDeserialization() throws IOException {
        XmlSerialization<Transport> xmlSerializer = new XmlSerialization<>(Transport.class);
        // Створюємо об'єкт Transport для тестування
        Transport transport = new Transport.Builder("Nissan", "GT-R", 2000.0)
                .setProductionYear(2024)
                .setPrice(120000.0)
                .build();

        // Серіалізуємо об'єкт в XML
        String serializedXml = xmlSerializer.serialize(transport);

        // Десеріалізуємо XML в об'єкт
        Transport deserializedTransport = xmlSerializer.deserialize(serializedXml);

        // Перевіряємо, що об'єкти збігаються
        Assert.assertEquals(deserializedTransport, transport);
    }

    @Test
    public void testWriteReadToFile() throws IOException {
        // Створюємо список уявних об'єктів автомобілів для серіалізації
        List<Transport> cars = new ArrayList<>();

        // Додаємо приклади об'єктів автомобілів
        cars.add(new Transport.Builder("Nissan", "GT-R", 2000.0)
                .setProductionYear(2024)
                .setPrice(120000.0)
                .build());
        cars.add(new Transport.Builder("Tesla", "Model 3", 2000.0)
                .setProductionYear(2023)
                .setPrice(70000.0)
                .build());
        cars.add (new Transport.Builder("Ford", "Mustang", 2000.0)
                .setProductionYear(2016)
                .setPrice(25000.0)
                .build());

        // Вказуємо шлях до тимчасового XML-файлу
        String filePath = "cars.xml";

        // Створюємо екземпляр XmlSerialization для об'єктів Transport
        XmlSerialization<Transport> xmlFormat = new XmlSerialization<>(Transport.class);

        // Записуємо список об'єктів автомобілів у файл
        xmlFormat.writeToFile(cars, filePath);

        // Зчитуємо з файлу і десеріалізуємо
        List<Transport> readCars = xmlFormat.readFromFile(filePath);

        // Порівнюємо оригінальний список та список, зчитаний з файлу
        Assert.assertEquals(cars, readCars);
    }
}
