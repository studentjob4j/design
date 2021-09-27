package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *Поиск файлов по критерию
 *  Программа должна искать данные в заданном каталоге и подкаталогах.
 * 3. Имя файла может задаваться, целиком, по маске, regex.
 * 4. Программа должна собираться в jar и запускаться через
 * java -jar find.jar -d=c:/ -n=*.txt -t=mask -o=log.txt
 * Ключи
 * -d - директория, в которой начинать поиск.
 * -n - имя файла, маска, либо регулярное выражение.
 * -t - тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению.
 * -o - результат записать в файл.
 * 5. Программа должна записывать результат в файл.
 * 6. В программе должна быть валидация ключей и подсказка.
 * @author Shegai Evgenii
 * @since 27.09.2021
 * @version 1.0
 */

public class SearchFilesByParametr {

    private static List<Path> list = new ArrayList<>();

    public static List<Path> getList() {
        return list;
    }

    public static void setList(List<Path> list) {
        SearchFilesByParametr.list = list;
    }

    public static void main(String[] args) throws IOException {
        if (!validKey(args)) {
            throw new IllegalArgumentException("Please check and  enter valid arguments");
        }
        SearchFiles searchFiles = typeOfFind(args);
        if (searchFiles == null) {
            throw new IllegalArgumentException("Please check the arguments");
        }
        Files.walkFileTree(Paths.get(getArgument(args, 0)), searchFiles);
        list = searchFiles.getList();
        RecAndcreateZip recAndcreateZip = new RecAndcreateZip();
        //Записываем данные в файл
        recAndcreateZip.recordInTheFIle(list);
        File destination = new File(getArgument(args, 3));
        // создаем зип архив
        recAndcreateZip.createZipFile(destination, list);
    }

    private static SearchFiles typeOfFind(String[] array) {
        if (getArgument(array, 2).equals("name")) {
            SearchFiles searcher = new SearchFiles(x -> x.endsWith(getArgument(array, 1)));
            return searcher;
        }
        if (getArgument(array, 2).equals("mask")) {
            Pattern pattern = Pattern.compile(getArgument(array, 1));
            //Matcher matcher = pattern.matcher();
            SearchFiles searcher = new SearchFiles(x -> pattern.matcher(x.toString()).find());
            return searcher;
        }
        return null;
    }

    private static boolean validKey(final String[] array) {
        boolean result = false;
        if (array.length != 4) {
            return result;
        } else if (array[0] != null || array[1] != null || array[2] != null || array[3] != null) {
            result = true;
        }
        return result;
    }

    private static String getArgument(String[] array, int value) {
        return array[value];
    }
}
