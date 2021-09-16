package ru.job4j.collection;

import org.junit.Test;
import ru.job4j.collection.map.SimpleMap;

import java.util.Iterator;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * SimpleMap
 * @author Evgenii Shegai
 * @since 16.09.2021
 * @version 1.0
 */

public class SimpleMapTest {

    @Test
    public void whenAddElement() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(12, "value");
        assertThat(map.get(12), is("value"));
    }

    @Test
    public void whenDeleteElement() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(12, "one");
        map.put(1, "two");
        map.remove(1);
        assertThat(map.get(12), is("one"));
        assertThat(map.getSize(), is(1));
    }

    @Test
    public void whenCreateIteratorThen() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(10, "one");
        map.put(20, "two");
        Iterator<String> it = map.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("two"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("one"));
        assertThat(it.hasNext(), is(false));
    }

}