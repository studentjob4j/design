package ru.job4j.collection;

/**
 * Simple LinkedList
 * @author Evgenii Shegai
 * @since 11.09.2021
 * @version 1.0
 */

public interface SimpleLinkedList<E> extends Iterable<E> {

    void add(E value);
    E get(int index);
}
