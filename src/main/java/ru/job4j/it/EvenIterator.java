package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Evgenii Shegai
 * @since 07.09.2021
 * @version 1.0
 */

public class EvenIterator implements Iterator<Integer> {

    private final int[] numbers;
    private int count;

    public EvenIterator(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean hasNext() {
        return this.exist() == 0;
    }

    @Override
    public Integer next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();

        }
        return this.numbers[this.count++];
    }

    private Integer exist() {
        int value = -1;
        for (int index = this.count; index < this.numbers.length; index++) {
            if (this.numbers[index] % 2 == 0) {
                this.count = index;
                value++;
                break;
            }
        }
        return value;
    }
}
