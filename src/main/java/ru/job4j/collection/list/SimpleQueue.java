package ru.job4j.collection.list;

import java.util.NoSuchElementException;

/**
 * Очередь на двух стеках
 * Если стек out пустой , то извлекаем все элементы из in .Везде использую принцип добавления в конец очереди
 * @author Shegai Evgenii
 * @since 27.09.2021
 * @version 1.0
 */

public class SimpleQueue<T> {

    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();


    public T poll() {
        if (!out.hasNext() && !in.hasNext()) {
            throw new NoSuchElementException();
        }
        if (!out.hasNext()) {
            while (in.getSize() != 0) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
    }

}
