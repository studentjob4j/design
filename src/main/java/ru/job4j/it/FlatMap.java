package ru.job4j.it;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Итератор итераторов
 * @author Evgenii Shegai
 * @since 07.09.2021
 * @version 1.0
 */

public class FlatMap<T> implements Iterator<T> {

    private final Iterator<Iterator<T>> data;
    private Iterator<T> cursor = Collections.emptyIterator();

    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;
        this.cursor = data.next();
    }

    /**
     * метод проверяет есть ли еще элементы в определенной ячейке итератора
     * перед указателем - !cursor.hasNext() и есть ли еще элементы в самом итератор итераторов
     * перед указателем data.hasNext() т.е если в определенной ячейке итератора нет уже элементов перед указателем
     * и еще есть куда двигаться по самому итератору итераторов то курсор передвигается на следующую ячейку
     * итератора итераторов cursor = data.next();
     * @return true or false
     */
    @Override
    public boolean hasNext() {
        while (!cursor.hasNext() && data.hasNext()) {
            cursor = data.next();
        }
        return  cursor.hasNext();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return cursor.next();
    }

    public static void main(String[] args) {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator(),
                List.of(4, 5, 6).iterator(),
                List.of(7, 8, 9).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        while (flat.hasNext()) {
            System.out.println(flat.next());
        }
    }
}
