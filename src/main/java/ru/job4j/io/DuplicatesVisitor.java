package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Поиск дубликатов
 * @author Evgenii Shegai
 * @since 20.09.2021
 * @version 1.0
 */

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Set<FileProperty> set = new HashSet<>();
    private List<FileProperty> list = new ArrayList<>();

    public List<FileProperty> getList() {
        return list;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        File temp = file.toFile();
            boolean rsl = set.add(new FileProperty(temp.length(), temp.getName()));
            if (!rsl) {
                list.add(new FileProperty(temp.length(), temp.getName()));
            }
        return FileVisitResult.CONTINUE;
    }

}
