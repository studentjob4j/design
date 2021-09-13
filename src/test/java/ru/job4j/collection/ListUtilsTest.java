package ru.job4j.collection;

import org.hamcrest.core.Is;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;
import static org.junit.Assert.*;

/**
 * ListIterator
 * @author Evgenii Shegai
 * @since 13.09.2021
 * @version 1.0
 */

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }
    @Test
    public void whenAddAfter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 4));
        ListUtils.addAfter(input, 1, 2);
        assertThat(Arrays.asList(1, 3, 2, 4), Is.is(input));
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addAfter(input, 3, 2);
    }
    @Test
    public void whenRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 6));
        Predicate<Integer> flter = x -> x > 5;
        List<Integer> result = ListUtils.removeIf(input, flter);
        assertThat(result.get(0), Is.is(6));
    }
    @Test
    public void whenReplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 6));
        Predicate<Integer> flter = x -> x <= 3;
        List<Integer> result = ListUtils.replaceIf(input, flter, 7);
        assertThat(result, Is.is(Arrays.asList(7, 7, 6)));
    }
    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 6));
        List<Integer> input2 = new ArrayList<>(Arrays.asList(1, 3));
        List<Integer> result = ListUtils.removeAll(input, input2);
        assertThat(result.get(0), Is.is(6));
    }

}