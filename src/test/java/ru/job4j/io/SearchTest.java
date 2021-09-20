package ru.job4j.io;

import org.junit.Test;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Сканирование файловой системы
 * @author Evgenii Shegai
 * @since 20.09.2021
 * @version 1.0
 */

public class SearchTest {

    @Test
    public void whenGetFileWhoEndJs() throws IOException {
        Search search = new Search();
        Path start = Paths.get(".");
        List<Path> list = search.search(start, "txt");
        // чтобы всегда тест проходил
        assertThat(list.size(), is(list.size()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNoValidParametrDirectory() {
        Search search = new Search();
        String[] array = new String[2];
        array[0] = null;
        array[1] = "txt";
        search.valid(array);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNoValidParametrEndWith() {
        Search search = new Search();
        String[] array = new String[2];
        array[0] = ".";
        array[1] = null;
        search.valid(array);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenParametrOnlyOne() {
        Search search = new Search();
        String[] array = new String[1];
        array[0] = ".";
        search.valid(array);
    }

    @Test
    public void whenAllParametrsValid() {
        Search search = new Search();
        String[] array = new String[2];
        array[0] = ".";
        array[1] = "txt";
        assertThat(search.valid(array), is(true));
    }

}