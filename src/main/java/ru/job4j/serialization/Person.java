package ru.job4j.serialization;

import java.util.Arrays;

/**
 * Преобразование JSON в POJO. JsonObject
 * @author Shegai Evgenii
 * @since 24.09.2021
 * @version 1.0
 */

public class Person {

    private boolean sex;

    private int age;

    private Contact contact;

    private String[] statuses;

    public Person() { }

    public Person(boolean sex, int age, Contact contact, String... statuses) {
        this.sex = sex;
        this.age = age;
        this.contact = contact;
        this.statuses = statuses;
    }

    public boolean isSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public Contact getContact() {
        return contact;
    }

    public String[] getStatuses() {
        return statuses;
    }

    @Override
    public String toString() {
        return "Person{"
                + "sex=" + sex
                + ", age=" + age
                + ", contact=" + contact
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }
}
