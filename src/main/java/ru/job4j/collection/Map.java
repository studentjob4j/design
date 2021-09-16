package ru.job4j.collection;

public interface Map<K, V> extends Iterable<V> {

    boolean put(K key, V value);
    V get(K key);
    boolean remove(K key);
}
