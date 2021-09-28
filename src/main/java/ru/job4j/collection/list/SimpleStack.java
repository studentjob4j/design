package ru.job4j.collection.list;

import java.util.Iterator;

/**
 * SimpleStack
 * @author Evgenii Shegai
 * @since 12.09.2021
 * @version 1.0
 */

public class SimpleStack<T> implements Iterator<T> {

    private ForwardLinked<T> linked = new ForwardLinked<T>();
    private int size;

    public int getSize() {
        return size;
    }

    public T pop() {
        size--;
        return this.linked.deleteFirst();
    }

    public void push(T value) {
        size++;
       this.linked.addFirst(value);
    }

    @Override
    public boolean hasNext() {
        return linked.iterator().hasNext();
    }

    @Override
    public T next() {
        return linked.iterator().next();
    }
}
