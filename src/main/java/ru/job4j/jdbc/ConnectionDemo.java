package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Shegai Evgenii
 * @since 07.10.2021
 * @version 1.0
 * Использую класс лоадер для чтения файла из папки ресурсы - загружаем его в пропертис файл -
 * читаем по ключ - значение из файла и получаю по ключу значения из пропертис . Получаю
 * объект конекшн и далее работаю с ним
 */

public class ConnectionDemo {

    private  final Properties properties = new Properties();

    public  String getValue(String key) {
        return this.properties.getProperty(key);
    }

    public  void load(InputStream is) {
        try {
            this.properties.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        ConnectionDemo demo = new ConnectionDemo();
        //Регистрация драйвера в системе
        Class.forName("org.postgresql.Driver");
        ClassLoader loader = ConnectionDemo.class.getClassLoader();
        try (InputStream is = loader.getResourceAsStream("app.properties")) {
            demo.load(is);
        }
        try (Connection connection = DriverManager.getConnection(demo.getValue("url"), demo.getValue("login"), demo.getValue("password"))) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
