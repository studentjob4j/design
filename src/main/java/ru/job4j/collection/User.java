package ru.job4j.collection;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Без переопределения иквалс и хешкод два объекта имеют разный хеш и лежат в разных бакетах
 * @author Evgenii Shegai
 * @since 16.09.2021
 * @version 1.0
 */

public class User {

    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Calendar calendar = new GregorianCalendar(2000, 5, 21);
        User one = new User("Natasha", 2, calendar);
        User two = new User("Natasha", 2, calendar);
        System.out.println(one.hashCode());
        System.out.println(two.hashCode());
        Map<User, Object> map = new HashMap<>();
        map.put(one, 1);
        map.put(two, 2);
        System.out.println(map.size());
        for (Map.Entry entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " Value: "
            + entry.getValue() + "  hashcode " + entry.hashCode());
        }
    }
}
