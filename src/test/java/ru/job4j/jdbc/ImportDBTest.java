package ru.job4j.jdbc;

import org.junit.Test;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ImportDBTest {

    @Test
    public void whenGetListUserFromDump() throws IOException {
        Properties properties = new Properties();
        ImportDB db = new ImportDB(properties, "././././data/dump.txt");
        List<ImportDB.User> userList = db.load();
        assertThat(userList.size(), is(5));
    }

}