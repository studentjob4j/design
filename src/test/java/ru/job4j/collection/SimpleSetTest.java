package ru.job4j.collection;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.collection.list.SimpleArray;
import ru.job4j.collection.set.Set;
import ru.job4j.collection.set.SimpleSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * SimpleSet
 * @author Evgenii Shegai
 * @since 14.09.2021
 * @version 1.0
 */

public class SimpleSetTest {

    private SimpleArray array = new SimpleArray<>(16);
    private SimpleSet set = new SimpleSet<>(array);

    @Before
    public void whenCreateSet() {
        array.add("one");
        array.add("six");
        array.add("two");
    }

    @Test
    public void whenAddDuplicateThen() {
        boolean result = set.add("six");
        assertThat(result, is(false));
    }

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>(new SimpleArray(16));
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>(new SimpleArray(16));
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

}