package ru.job4j.collection;

/**
 * SimpleStack
 * @author Evgenii Shegai
 * @since 12.09.2021
 * @version 1.0
 */

public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<T>();

    public T pop() {
        return this.linked.deleteFirst();
    }

    public void push(T value) {
       this.linked.addFirst(value);
    }
}
