package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

/**
 * Поиск файлов по критерию
 * @author Shegai Evgenii
 * @since 27.09.2021
 * @version 1.0
 */

public class RecAndcreateZip {

    public void recordInTheFIle(List<Path> list) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(
                "data/searchFilesByParametr/results.txt")))) {
            list.stream().forEach(x -> out.println(x.toFile().getAbsolutePath()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void createZipFile(File file, List<Path> list) {
        Zip.packFiles(list, file);
    }
}
