package ru.job4j.io;

import org.junit.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Поиск дубликатов
 * @author Evgenii Shegai
 * @since 20.09.2021
 * @version 1.0
 */

public class DuplicatesVisitorTest {

    @Test
    public void whenFindDuplicatesInProject() throws IOException {
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("c:/projects/design/"), visitor);
        assertThat(visitor.getList().size(), is(visitor.getList().size()));
    }
}