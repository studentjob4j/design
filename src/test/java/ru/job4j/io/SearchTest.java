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
        assertThat(list.size(), is(23));
    }

}