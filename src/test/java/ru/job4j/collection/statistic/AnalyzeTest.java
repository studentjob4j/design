package ru.job4j.collection.statistic;

import org.junit.Test;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Статистика по колекции
 * @author Shegai Evgenii
 * @since 16.09.2021
 * @version 1.0
 */

public class AnalyzeTest {

    @Test
    public void whenDiffList() {
        List<User> previous = List.of(
                new User(1, "one"),
                new User(2, "two"),
                new User(3, "three"),
                new User(4, "four"),
                new User(5, "five")
        );
        List<User> current = List.of(
                new User(1, "change"),
                new User(2, "two"),
                new User(6, "add"),
                new User(4, "change")
        );
        Analyze analyze = new Analyze();
        Info info = analyze.diff(previous, current);
        assertThat(info.getAdded(), is(1));
        assertThat(info.getChanged(), is(2));
        assertThat(info.getDeleted(), is(2));
        assertThat(info.getOld(), is(1));
    }

}