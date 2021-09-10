package ru.job4j.collection;

/**
 * SimpleArrayList
 * @author Evgenii Shegai
 * @since 10.09.2021
 * @version 1.0
 */

public interface List<T> extends Iterable<T> {

    void add(T value);
    T set(int index, T newValue);
    T remove(int index);
    T get(int index);
    int size();
}
