package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Архивирование проекта
 * @author Evgenii Shegai
 * @since 20.09.2021
 * @version 1.0
 */


public class Zip {

    public static void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path tmp : sources) {
                zip.putNextEntry(new ZipEntry(String.valueOf((tmp))));

                try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(tmp.toFile()))) {
                    zip.write(bis.readAllBytes());
                }
                zip.closeEntry();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        ArgZip argZip = new ArgZip(args);
        if (argZip.valid()) {
            Search search = new Search();
            List<Path> list = search.search2(Paths.get(argZip.directory()), argZip.exclude());
            File value = new File(argZip.output());
            Zip zip = new Zip();
            zip.packFiles(list, value);
        }
    }
}
