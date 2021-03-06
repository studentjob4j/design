package ru.job4j.collection;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

public interface Tree<E> {

    boolean add(E parent, E child);

    Optional<Node<E>> findBy(E value);

    class Node<E> {
        final E value;
        final List<Node<E>> children = new ArrayList<>();

        public Node(E value) {
            this.value = value;
        }
    }
}
