package ru.job4j.io;

import org.junit.Test;
import java.io.BufferedReader;
import java.io.FileReader;
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
}