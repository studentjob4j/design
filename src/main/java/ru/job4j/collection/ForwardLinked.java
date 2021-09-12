package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Удаление head в односвязанном списке
 * @author Evgenii Shegai
 * @since 12.09.2021
 * @version 1.0
 */

public class ForwardLinked<T> implements Iterable<T> {

    private Node<T> head;
    private Node<T> result;
    private Node<T> tail;

    public void addToEnd(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            tail = node;
            return;
        }
        tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
        tail = node;
    }

    /**
     * Получаем 1 элемент из стека и хед переходит на следующую ноду
     * @return T
     */

    public T deleteFirst() {
        Node<T> temp = null;
        if (head == null) {
            throw new NoSuchElementException();
        }
        result = head;
        head = temp;
        head = result.next;
        return result.value;
    }

    /**
     * Добавляем элемент в стек и хед переносится в добавленный элемент
     * @param value значение новой ноды
     */
    public void addFirst(T value) {
        Node<T> newNode = new Node(value, null);
        if (head == null) {
            head = newNode;
            return;
        }
        Node<T> second = head;
        head = newNode;
        head.next = second;
    }

    /**
     * Метод переворачивает список
     */
    public boolean revert() {
        if (head == null || head.next == null) {
            return false;
        }
            tail = head;
            Node<T> current = head.next;
            head.next = null;
        while (current != null) {
             Node<T> next = current.next;
             current.next = head;
             head = current;
             current = next;
        }
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
