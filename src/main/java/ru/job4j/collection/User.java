package ru.job4j.collection;

import java.util.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Без переопределения equals and hashcode   два объекта имеют разный хеш и лежат в разных бакетах
 *
 *  Без переопределения иквалс - два объекта имеют одинаковый хеш но лежат в разных бакетах
 * т.к в hashmap у них разные хеши .
 *
 * Переопределить только equals - hashcode разный значит объекты попали в разные бакеты и equals
 * не вызывался
 *
 * Когда переопределил equals and hashcode то в карте всео один объект
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name)
                && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        Calendar calendar = new GregorianCalendar(2000, 5, 21, 11, 12, 22);
        User one = new User("Natasha", 2, calendar);
        User two = new User("Natasha", 2, calendar);
        System.out.println(one.hashCode());
        System.out.println(two.hashCode());
        Map<User, Object> map = new HashMap<>();
        map.put(one, new Object());
        map.put(two, new Object());
        System.out.println(map.size());
        for (Map.Entry entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " Value: "
            + entry.getValue() + "  hashcode " + entry.hashCode());
        }
    }
}
