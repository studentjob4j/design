package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Simple LinkedList
 * @author Evgenii Shegai
 * @since 11.09.2021
 * @version 1.0
 */

public class LinkedList<E> implements SimpleLinkedList<E> {

    private Node<E> first;
    private int size = 0;
    private int modCount = 0;

    @Override
    public void add(E value) {
        if (size == 0) {
            this.first = new Node<>(value);
        } else {
            getNode(size - 1).next = new Node<>(value);
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        return getNode(index).data;
    }

    private Node<E> getNode(int index) {
        Node<E> nodeTemp = this.first;
        if (Objects.checkIndex(index, size) == index) {
            for (int i = 0; i < index; i++) {
                nodeTemp = nodeTemp.next;
            }
        }
        return nodeTemp;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int expectedmodCount = modCount;
            private Node<E> current = first;
            private E result;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (this.expectedmodCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                    result = current.data;
                    current = current.next;

                return result;
            }
        };
    }

    private class Node<E> {
        E data;
        Node<E> next;
        Node(E data) {
            this.data = data;
        }
    }
}
