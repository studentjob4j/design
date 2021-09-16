package ru.job4j.collection.map;

import ru.job4j.collection.map.Map;

import java.util.Iterator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * SimpleMap
 * @param <K> key
 * @param <V> value
 * @author Evgenii Shegai
 * @since 16.09.2021
 * @version 1.0
 */

public class SimpleMap<K, V> implements Map<K, V> {

    private Node[] array;
    private int load;
    private int modCount;
    private int size;
    public final double loadFactor = 0.75;

    public SimpleMap() {
        this.array = new Node[16];
        load = (int) (array.length * loadFactor);
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        Node<K, V> temp = new Node<>(key, value);
        if (size + 1 >= load) {
            load *= 2;
            expensive();
        }
        int index = hash(key);
        if (array[index] == null) {
            array[index] = temp;
            result = true;
            size++;
            modCount++;
        }
        return result;
    }

    private void expensive() {
        Node[] temp = array;
        array = new Node[temp.length * 2];
        size = 0;
        for (Node tmp : temp) {
            if (tmp != null) {
                put((K) tmp.getKey(), (V) tmp.getValue());
            }
        }
    }

    public int hash(K key) {
        int temp = 31;
        temp = temp * 17 + key.hashCode();
        return temp % array.length;
    }

    @Override
    public V get(K key) {
        V value = null;
            if (array[hash(key)] != null && array[hash(key)].getKey().equals(key)) {
                value = (V) array[hash(key)].getValue();
            }
            return value;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        if (array[hash(key)] != null && array[hash(key)].getKey().equals(key)) {
            array[hash(key)] = null;
            size--;
            modCount++;
            result = true;
        }
        return result;
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {

            int expectedModCount = modCount;
            int currentNodeNumber = 0;
            Node currentNode;
            int currentArrayIndex = 0;

            @Override
            public boolean hasNext() {
                return currentNodeNumber < size;
            }

            @Override
            public V next() {
                V value = null;
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                for (; currentNodeNumber != size; currentArrayIndex++) {
                    if (array[currentArrayIndex] != null) {
                        currentNode = array[currentArrayIndex];
                        currentNodeNumber++;
                        value = (V) currentNode.getValue();
                        currentArrayIndex++;
                        return value;
                    }
                }
                return null;
            }
        };
    }

    private  static class Node<K, V> {
        K key;
        V value;
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public K getKey() {
            return key;
        }
        public V getValue() {
            return value;
        }
    }
}
