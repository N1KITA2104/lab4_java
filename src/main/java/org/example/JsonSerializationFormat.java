package org.example;

import com.google.gson.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class JsonSerializationFormat<T> implements SerializationFormat<T> {

    private final Class<T> type;

    public JsonSerializationFormat(Class<T> type) {
        this.type = type;
    }

    @Override
    public String serialize(T object) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(object);
    }

    @Override
    public T deserialize(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }

    public void writeToFile(List<T> objects, String filePath) throws IOException {
        try (Writer writer = new FileWriter(filePath)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(objects);
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<T> readFromFile(String filePath) throws IOException {
        try (FileReader reader = new FileReader(filePath)) {
            StringBuilder jsonBuilder = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                jsonBuilder.append((char) c);
            }
            String json = jsonBuilder.toString();

            Gson gson = new Gson();
            JsonParser jsonParser = new JsonParser();
            JsonArray jsonArray = JsonParser.parseString(json).getAsJsonArray();

            List<T> list = new ArrayList<>();
            for (JsonElement element : jsonArray) {
                T obj = gson.fromJson(element, type);
                list.add(obj);
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
