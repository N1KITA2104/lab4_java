package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TxtSerializationFormat<T> implements SerializationFormat<T> {

    @Override
    public String serialize(T object) {
        // Преобразование объекта в строку и форматирование
        StringWriter writer = new StringWriter();
        try (PrintWriter out = new PrintWriter(writer)) {
            // Запись объекта в поток
            if (object instanceof Transport) {
                Transport transport;
                transport = (Transport) object;
                out.println("Brand: " + transport.getBrand());
                out.println("Model: " + transport.getModel());
                out.println("Transport Weight: " + transport.getWeight());
                out.println("Production Year: " + transport.getProductionYear());
                out.println("Price: " + transport.getPrice());
            }
        }
        return writer.toString();
    }

    @Override
    public T deserialize(String data) throws IOException {
        // Реализация десериализации из строки в объект
        BufferedReader reader = new BufferedReader(new StringReader(data));
        String brand = reader.readLine().substring("Brand: ".length());
        String model = reader.readLine().substring("Model: ".length());
        double transportWeight = Double.parseDouble(reader.readLine().substring("Transport Weight: ".length()));
        int productionYear = Integer.parseInt(reader.readLine().substring("Production Year: ".length()));
        double price = Double.parseDouble(reader.readLine().substring("Price: ".length()));

        // Создание объекта Transport
        Transport transport = new Transport.Builder(brand, model, transportWeight)
                .setProductionYear(productionYear)
                .setPrice(price)
                .build();
        return (T) transport;
    }

    @Override
    public void writeToFile(List<T> objects, String filePath) throws IOException {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            for (T object : objects) {
                String serializedData = serialize(object);
                writer.println(serializedData);
                writer.println(); // Добавляем пустую строку между объектами
            }
        }
    }

    @Override
    public List<T> readFromFile(String filePath) throws IOException {
        List<T> objects = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder serializedData = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) { // Check if the line is not empty or only contains whitespace
                    serializedData.append(line).append("\n");
                } else if (serializedData.length() > 0) {
                    // Deserialize and add the object to the list
                    T object = deserialize(serializedData.toString().trim());
                    objects.add(object);
                    serializedData.setLength(0); // Clear the buffer for the next object
                }
            }

            // Deserialize the last object if it doesn't end with an empty line
            if (serializedData.length() > 0) {
                T object = deserialize(serializedData.toString().trim());
                objects.add(object);
            }
        }

        return objects;
    }
}