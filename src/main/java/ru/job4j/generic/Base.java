package ru.job4j.generic;

/**
 * Универсальное хранилище Store
 * @author Evgenii Shegai
 * @since 08.09.2021
 * @version 1.0
 */

public abstract class Base {

    private final String id;

    public Base(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
