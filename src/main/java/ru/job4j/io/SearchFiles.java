package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Сканирование файловой системы
 * @author Evgenii Shegai
 * @since 20.09.2021
 * @version 1.0
 */

public class SearchFiles extends SimpleFileVisitor<Path> {

    private List<Path> list = new ArrayList<>();
    private Predicate<String> predicate;

    public SearchFiles(Predicate<String> predicate) {
        this.predicate = predicate;
    }

    public List<Path> getList() {
        return list;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Path temp = file.getFileName();
        if (predicate.test(temp.toString())) {
            list.add(temp);
        }
        return FileVisitResult.CONTINUE;
    }

}
