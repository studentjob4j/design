package ru.job4j.collection;

import org.junit.Test;
import ru.job4j.collection.list.LinkedList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Simple LinkedList
 * @author Evgenii Shegai
 * @since 11.09.2021
 * @version 1.0
 */

public class LinkedListTest {

    @Test
    public void whenAddAndGet() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        assertThat(list.get(0), is(1));
        assertThat(list.get(1), is(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetFromOutOfBoundThenExceptionThrown() {
       LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.get(2);
    }

    @Test
    public void whenGetIteratorTwiceThenEveryFromBegin() {
      LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);

        Iterator<Integer> first = list.iterator();
        assertThat(first.hasNext(), is(true));
        assertThat(first.next(), is(1));
        assertThat(first.hasNext(), is(true));
        assertThat(first.next(), is(2));
        assertThat(first.hasNext(), is(false));

        Iterator<Integer> second = list.iterator();
        assertThat(second.hasNext(), is(true));
        assertThat(second.next(), is(1));
        assertThat(second.hasNext(), is(true));
        assertThat(second.next(), is(2));
        assertThat(second.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenModCountItFromIteratorNotEqualModCount() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        Iterator<Integer> it = list.iterator();
        assertThat(it.next(), is(1));
        list.add(4);
        it.next();
    }
}