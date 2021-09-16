package ru.job4j.collection.list;

import ru.job4j.collection.list.List;

import java.util.*;

/**
 * SimpleArrayList
 * @author Evgenii Shegai
 * @since 10.09.2021
 * @version 1.0
 */

public class SimpleArray<T> implements List<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArray(int capacity) {
        try {
            this.container = (T[]) new Object[capacity];
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            expansionArray();
        }
        container[size++] = value;
        modCount++;
    }

    private void expansionArray() {
        container = Arrays.copyOf(container, container.length * 2);
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T result = container[index];
        container[index] = newValue;
        return result;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T result = container[index];
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        container[container.length - 1] = null;
        size--;
        return result;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int position;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return position < size;
            }

            @Override
            public T next() {
                T result = null;
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                } else {
                    result = container[position++];
                }
                return result;
            }
        };
    }

    public static void main(String[] args) {
        String[] array = {"one", "two", "three", "four", "five"};
        System.out.println(Arrays.toString(array));
        // индекс по которому удаляем
        int index = 2;
        System.arraycopy(
                array, // откуда копируем
                index + 1, // начиная с какого индекса
                array, // куда копируем
                index, // начиная с какого индекса
                array.length - index - 1 // сколько элементов копируем
        );
        // на последнее место ставим null, чтобы не было учечки памяти (если удаляем последний элемент)
        array[array.length - 1] = null;
        System.out.println(Arrays.toString(array));
    }

}
