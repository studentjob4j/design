package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Поиск дубликатов
 * @author Evgenii Shegai
 * @since 20.09.2021
 * @version 1.0
 */

public class DuplicatesFinder {

    public static void main(String[] args) throws IOException {
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("c:/projects/design/"), visitor);
        visitor.getList().stream().forEach(System.out::println);
    }
}
