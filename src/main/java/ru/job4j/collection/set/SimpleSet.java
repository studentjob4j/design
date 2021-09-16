package ru.job4j.collection.set;

import ru.job4j.collection.list.SimpleArray;

import java.util.Iterator;
import java.util.Objects;

/**
 * SimpleSet
 * @author Evgenii Shegai
 * @since 14.09.2021
 * @version 1.0
 */

public class SimpleSet<T> implements Set<T> {

    private SimpleArray array = new SimpleArray(16);

    public SimpleSet(SimpleArray array) {
        this.array = array;
    }

    public boolean add(T value) {
        boolean result = false;
            if (!contains(value)) {
                array.add(value);
                result = true;
            }
            return result;
        }

        public boolean contains(T value) {
            Iterator<T> it = array.iterator();
            while (it.hasNext()) {
                if (Objects.equals(value, it.next())) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public Iterator<T> iterator() {
            return array.iterator();
        }
}
