package org.example;

import java.io.IOException;
import java.util.List;

public interface SerializationFormat<T> {

    /**
     * Сериализация объекта в строку в соответствующем формате.
     *
     * @param object объект для сериализации
     * @return строковое представление объекта в формате
     * @throws IOException если произошла ошибка ввода/вывода
     */
    String serialize(T object) throws IOException;

    /**
     * Десериализация объекта из строки в соответствующем формате.
     *
     * @param data строковое представление объекта в формате
     * @return объект, созданный из строки
     * @throws IOException если произошла ошибка ввода/вывода
     */
    T deserialize(String data) throws IOException;

    /**
     * Запись списка объектов в файл в соответствующем формате.
     *
     * @param objects список объектов для записи
     * @param filePath путь к файлу для записи
     * @throws IOException если произошла ошибка ввода/вывода
     */
    void writeToFile(List<T> objects, String filePath) throws IOException;

    /**
     * Чтение списка объектов из файла в соответствующем формате.
     *
     * @param filePath путь к файлу для чтения
     * @return список объектов, прочитанных из файла
     * @throws IOException если произошла ошибка ввода/вывода
     */
    List<T> readFromFile(String filePath) throws IOException;
}

