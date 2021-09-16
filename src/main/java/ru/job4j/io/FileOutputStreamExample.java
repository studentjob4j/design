package ru.job4j.io;

import java.io.FileOutputStream;

/**
 * @author Shegai Evgenii
 * @since 16.09.2021
 * @version 1.0
 */

public class FileOutputStreamExample {

    public static int[][] multiple(int size) {
        int[][] result = new int[size][size];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = (i + 1) * (j + 1);
            }
        }
        return result;
    }

    private static byte[] convert(int[][] array) {
        String result = "";
        for (int[] row : array) {
            for (int cell : row) {
                String temp = Integer.toString(cell);
                result += temp + System.lineSeparator();
            }
        }
        return result.getBytes();
    }

    public static void main(String[] args) {
            try (FileOutputStream out = new FileOutputStream("result.txt")) {
                out.write(convert(multiple(2)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
