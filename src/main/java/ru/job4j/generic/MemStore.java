package ru.job4j.generic;

import java.util.HashMap;
import java.util.Map;

/**
 * Универсальное хранилище Store
 * @author Evgenii Shegai
 * @since 08.09.2021
 * @version 1.0
 */

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> mem = new HashMap<>();

    @Override
    public void add(T model) {
       mem.put(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        return mem.replace(id, model) != null;
    }

    @Override
    public boolean delete(String id) {
        return mem.remove(id, findById(id));
    }

    @Override
    public T findById(String id) {
        return mem.get(id);
    }
}
