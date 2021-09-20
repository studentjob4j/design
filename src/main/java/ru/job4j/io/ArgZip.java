package ru.job4j.io;

/**
 * Архивирование проекта
 * @author Evgenii Shegai
 * @since 20.09.2021
 * @version 1.0
 */

public class ArgZip {

    private final String[] args;

    public ArgZip(String[] args) {
        this.args = args;
    }

    public boolean valid() {
        if (args.length != 3) {
            throw new IllegalArgumentException("Wrong arguments please enter correct arguments");
        }
        return true;
    }

    public String directory() {
        return args[0];
    }

    public String exclude() {
        return args[1];
    }

    public String output() {
        return args[2];
    }
}
