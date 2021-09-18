package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 *  Анализ доступности сервера
 * @author Evgenii Shegai
 * @since 18.09.2021
 * @version 1.0
 */

public class AnalizyTest {


    /**
     * класс TemporaryFolder нужно использовать, когда программа записывает результат в файл.
     */
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenServerNotWorkSomeTime() {
        Analizy analizy = new Analizy();
        String log = "data/log.txt";
        String target = "data/target.txt";
        analizy.unavailable(log, target);
        List<String> expect = new ArrayList<>();
        expect.add("10:57:01;10:59:01" + System.lineSeparator());
        expect.add("11:01:02;11:02:02" + System.lineSeparator());
        List<String> result = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader("data/target.txt"))) {
            while (read.ready()) {
                result.add(read.readLine() + System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(result, is(expect));
    }

    /**
     * Чтобы создать файлы во временной директории, нужно использовать класс org.unit.rules.TemporaryFolder.
     * Этот класс позволяет создавать файлы и директории во временном каталоге.
     * После запуска теста файлы созданные через TemporaryFolder будут удалены.
     * Теперь нам нет необходимости заботиться о мусоре, который оставляет наш тест, потому что его
     * уберет класс  TemporaryFolder.
     */

    @Test
    public void whenUseTemporaryFolder() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        // читаем файл из лога и записываем его далее в источник
        try (PrintWriter out = new PrintWriter(source);
             BufferedReader in = new BufferedReader(new FileReader("data/log.txt"))) {
            while (in.ready()) {
                out.write(in.read());
            }
        }

        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        // читаем из таргета и добавляем в билдер
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(x -> rsl.append(x + System.lineSeparator()));
        }
        StringBuilder builder = new StringBuilder();
        builder.append("10:57:01;10:59:01" + System.lineSeparator());
        builder.append("11:01:02;11:02:02" + System.lineSeparator());
        assertThat(rsl.toString(), is(builder.toString()));
    }
}