package ru.job4j.io;

import java.io.FileInputStream;

/**
 * FileInputStream
 * @author Shegai Evgenii
 * @since 16.09.2021
 * @version 1.0
 */

public class EvenNumberFile {

    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String temp : lines) {
                int res = Integer.parseInt(temp);
                if (res % 2 == 0) {
                    System.out.println(res + " even number");
                } else {
                    System.out.println(res + " not even number");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
