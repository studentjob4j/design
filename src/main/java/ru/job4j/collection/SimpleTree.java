package ru.job4j.collection;

import java.util.Optional;
import java.util.Queue;
import java.util.LinkedList;
import java.util.function.Predicate;

/**
 * SimpleTree
 * @author Evgenii Shegai
 * @since 1.09.2021
 * @version 1.0
 */

public class SimpleTree<E> implements Tree<E> {

    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        if (!findBy(child).isPresent()) {
            Node<E> temp = findBy(parent).get();
            if (temp != null) {
                temp.children.add(new Node<E>(child));
                rsl = true;
            }
        }
        return rsl;
    }

    /**
     * алгоритм обхода дерева в ширину используем очередь
     * @param value
     * @return нода
     */

    @Override
    public Optional<Node<E>> findBy(E value) {
       return findByPredicate(x ->
          x.value.equals(value));
    }

    public boolean isBinary() {
        return !findByPredicate(x -> x.children.size() > 2).isPresent();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

}
