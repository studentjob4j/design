package ru.job4j.collection.list;

import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * ListIterator
 * @author Evgenii Shegai
 * @since 13.09.2021
 * @version 1.0
 */

public class ListUtils {

    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
         ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (i.nextIndex() == index) {
                i.add(value);
                break;
            }
            i.next();
        }
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (i.previousIndex() == index) {
                i.add(value);
            }
            i.next();
        }
    }

    public static <T> List<T> removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> it = list.listIterator();
        while (it.hasNext()) {
                if (!filter.test(it.next())) {
                    it.remove();
                }
            }
            return list;
        }

    public static <T> List<T> replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> it = list.listIterator();
        while (it.hasNext()) {
            if (filter.test(it.next())) {
                it.set(value);
            }
        }
        return list;
    }

    public static <T> List<T> removeAll(List<T> list, List<T> elements) {
        ListIterator<T> it = elements.listIterator();
        int count = 0;
        while (it.hasNext()) {
            if (list.contains(it.next())) {
                int index = it.nextIndex();
                list.remove(--index - count);
                count++;
            }
        }
        return list;
    }
}
