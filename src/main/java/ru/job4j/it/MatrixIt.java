package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Обратный итератор
 * @author Evgenii Shegai
 * @since 07.09.2021
 * @version 1.0
 */

public class MatrixIt implements Iterator<Integer> {

    private final int[][] data;
    private int row = 0;
    private int col = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (row < data.length && data[row].length == col) {
            row++;
            col = 0;
        }
        return row < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][col++];
    }
}
