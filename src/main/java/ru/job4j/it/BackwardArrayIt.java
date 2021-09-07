package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Обратный итератор
 * @author Evgenii Shegai
 * @since 07.09.2021
 * @version 1.0
 */

public class BackwardArrayIt implements Iterator<Integer> {

    private final int[] data;
    private int point = 0;

    public BackwardArrayIt(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return point < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int index = data.length - point - 1;
        point++;
        return data[index];
    }
}
