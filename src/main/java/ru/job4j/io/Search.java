package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Сканирование файловой системы
 * @author Evgenii Shegai
 * @since 20.09.2021
 * @version 1.0
 */

public class Search {

    // метод ищет в файловой системе файлы которые отвечают предикату

    public List<Path> search(Path root, String ext) throws IOException {
        SearchFiles searcher = new SearchFiles(x -> x.endsWith(ext));
        Files.walkFileTree(root, searcher);
        return searcher.getList();
    }

    public static void main(String[] args) throws IOException {
        valid(args);
        SearchFiles searcher = new SearchFiles(x -> x.endsWith(args[1]));
        Files.walkFileTree(Paths.get(args[0]), searcher);
        searcher.getList()
                .stream()
                .forEach(x -> System.out.println(x.toFile().getName()));
    }

    public static boolean valid(String[] values) {
        boolean rsl = true;
        if (values.length < 2 ||  values[0] == null || values[1] == null) {
            throw new IllegalArgumentException("Wrong arguments");
        }
        return rsl;
    }
}

