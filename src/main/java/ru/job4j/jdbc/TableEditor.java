package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

/**
 * @author Shegai Evgenii
 * @since 07.10.2021
 * @version 1.0
 * Создаем объект класса используя класс пропертис для чтения настроек подключения к бд и инициализируем
 * подлкючение к бд получаем объект конекшн и далее получаем объект стетмент в методе экзекют и работаем с
 * ним передавая ему команды в виде строки
 */

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            ClassLoader loader = TableEditor.class.getClassLoader();
            try (var is = loader.getResourceAsStream("statement.properties")) {
                load(is);
            }
            this.connection = DriverManager.getConnection(this.properties.getProperty("url"), this.properties.getProperty("login"), this.properties.getProperty("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  void load(InputStream is) {
        try {
            this.properties.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void execute(String value) {
        try (Statement statement = this.connection.createStatement()) {
            statement.execute(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        String temp = String.format("create table %s (%s, %s);", tableName, "id serial primary key", "name varchar(255)"
        );
        execute(temp);
    }

    public void dropTable(String tableName) {
        String temp = String.format("drop table %s ;", tableName);
        execute(temp);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String temp = String.format("alter table %s add column %s %s;", tableName, columnName, type);
        execute(temp);
    }

    public void dropColumn(String tableName, String columnName) {
        String temp = String.format("alter table %s drop column %s;", tableName, columnName);
        execute(temp);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String temp = String.format("alter table %s rename column %s to %s;", tableName, columnName, newColumnName);
        execute(temp);
    }

    public void getTable(String tableName) {
        String temp = String.format("select * from %s", tableName);
        execute(temp);
    }


    public String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties pro = new Properties();
        TableEditor editor = new TableEditor(pro);
        editor.createTable("works");
        editor.addColumn("works", "salary", "int");
        System.out.println(editor.getTableScheme(editor.connection, "works"));
        editor.renameColumn("works", "salary", "newSalary");
        System.out.println(editor.getTableScheme(editor.connection, "works"));
        editor.dropColumn("works", "newsalary");
        editor.getTable("works");
        editor.dropTable("works");
        editor.close();
    }
}
