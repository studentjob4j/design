package ru.job4j.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Shegai Evgenii
 * @since 10.10.2021
 * @version 1.0
 * Принцип работы - читаем файл дамп - читаем лист юзеров - создаем таблицу и вставляем туда данные
 * использу стейтмент - удаляем таблицу по желанию
 *
 */

public class ImportDB {

    private Properties properties;
    private String dump;
    private Connection connection;

    public ImportDB(Properties cfg, String dump) {
        this.properties = cfg;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        String line = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(dump))) {
            while ((line = bufferedReader.readLine()) != null) {
                String[] array = line.split(";");
                User user = new User(array[0], array[1]);
                users.add(user);
            }
        }
        return users;
    }

    public void createTable(String tableName) throws SQLException, ClassNotFoundException, IOException {
        init();
        try (Statement statement = connection.createStatement()) {
            statement.execute(String.format("create table %s (%s, %s, %s);", tableName, "id serial primary key", "name varchar(20)", "email varchar(50)"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
    }

    public void drop(String tableName) throws SQLException, IOException, ClassNotFoundException {
        init();
        try (Statement statement = connection.createStatement()) {
            statement.execute(String.format("drop table %s;", tableName));
        }
        close();
    }

    private void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    private void init() throws ClassNotFoundException, SQLException, IOException {
        ClassLoader loader = ImportDB.class.getClassLoader();
        try (InputStream is = loader.getResourceAsStream("users.properties")) {
            properties.load(is);
        }
        Class.forName(properties.getProperty("jdbc.driver"));
        connection = DriverManager.getConnection(
                properties.getProperty("jdbc.url"),
                properties.getProperty("jdbc.username"),
                properties.getProperty("jdbc.password"));

    }

    public void save(List<User> users) throws SQLException, ClassNotFoundException, IOException {
        init();
        for (User user : users) {
            try (PreparedStatement ps = connection.prepareStatement("insert into users(name, email) VALUES (?, ?);")) {
                ps.setString(1, user.getName());
                ps.setString(2, user.getEmail());
                ps.execute();
            }
        }
        close();
    }

     static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }
    }

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        Properties properties = new Properties();
        ImportDB db = new ImportDB(properties, "data/dump.txt");
        List<User> result = db.load();
        db.createTable("users");
        db.save(result);
        db.drop("users");
    }
}
