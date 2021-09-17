package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Читаем файл конфигурации
 * @author Evgenii Shegai
 * @since 17.09.2021
 * @version 1.0
 */

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("John Connor"));
    }

    @Test
    public void whenWithComment() {
        String path = "data/with_comments.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key"), is("value"));
    }

    @Test
    public void whenWithoutValue() {
        String path = "data/key_without_value.properties";
        Config config = new Config(path);
        config.load();
    }

}