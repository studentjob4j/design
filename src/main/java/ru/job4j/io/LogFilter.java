package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * BufferedReader.
 * @author Shegai Evgenii
 * @since 16.09.2021
 * @version 1.0
 */

public class LogFilter {

    public static List<String> filter(String file) {
        List<String> result = new ArrayList<>();
        String temp = null;
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            temp = in.readLine();
            while (temp != null) {
                String value = findRows(temp);
                if (value == null) {
                    temp = in.readLine();
                    continue;
                }
                result.add(value);
                temp = in.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String findRows(String value) {
        String result = null;
        String[] tmp = value.split(" ");
            if (tmp[8].equals("404")) {
                result = value;
            }
        return result;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        log.stream().forEach(x -> System.out.println(x));
    }
}
