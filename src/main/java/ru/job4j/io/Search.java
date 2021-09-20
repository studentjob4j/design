package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
}
