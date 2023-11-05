package org.example;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class XmlSerialization<T> implements SerializationFormat<T> {
    private XmlMapper xmlMapper;
    private Class<T> objectClass;

    public XmlSerialization(Class<T> objectClass) {
        this.objectClass = objectClass;
        xmlMapper = new XmlMapper();
    }

    @Override
    public String serialize(T object) throws IOException {
        return xmlMapper.writeValueAsString(object);
    }

    @Override
    public T deserialize(String data) throws IOException {
        return xmlMapper.readValue(data, objectClass);
    }

    @Override
    public void writeToFile(List<T> objects, String filePath) throws IOException {
        xmlMapper.writeValue(new File(filePath), objects);
    }

    @Override
    public List<T> readFromFile(String filePath) throws IOException {
        return xmlMapper.readValue(new File(filePath), xmlMapper.getTypeFactory().constructCollectionType(List.class, objectClass));
    }
}