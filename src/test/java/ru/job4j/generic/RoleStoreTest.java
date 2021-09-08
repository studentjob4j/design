package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Универсальное хранилище Store
 * @author Evgenii Shegai
 * @since 08.09.2021
 * @version 1.0
 */

public class RoleStoreTest {

    private RoleStore store = new RoleStore();

    @Before
    public void whenCreateData() {
        store.add(new Role("google"));
        store.add(new Role("yandex"));
        store.add(new Role("mailru"));
    }

    @Test
    public void whenReplace() {
        boolean result = store.replace("google", new Role("facebook"));
        assertThat(result, is(true));
    }

    @Test
    public void whenDelete() {
        boolean result = store.delete("yandex");
        assertThat(result, is(true));
    }

    @Test
    public void whenFind() {
        Role result = store.findById("mailru");
        assertThat(result.getId(), is("mailru"));
    }

}