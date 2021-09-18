package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  Анализ доступности сервера
 * @author Evgenii Shegai
 * @since 18.09.2021
 * @version 1.0
 */

public class Analizy {

    public void unavailable(String source, String target) {

        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            List<String> list = new ArrayList<>();
            list = findTime(read, list);
            list.stream().forEach(x -> out.write(x));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<String> findTime(BufferedReader reader, List<String> list) throws Exception {
        String temp = reader.readLine();
        int count = 0;
        boolean flag = false;
        while (temp != null) {
            if (temp.contains("500") || temp.contains("400") && !flag) {
                for (String tmp : temp.split(" ")) {
                    if (count == 1) {
                        list.add(tmp + ";");
                        flag = true;
                        count = 0;
                        continue;
                    }
                    count = 1;
                    continue;
                }
            } else if ((temp.contains("200") || temp.contains("300")) && flag) {
                for (String tmp : temp.split(" ")) {
                    if (count == 1) {
                        list.add(tmp + System.lineSeparator());
                        count = 0;
                        continue;
                    }
                    count = 1;
                    continue;
                }
            }
            temp = reader.readLine();
            if (reader.ready()) {
                while (temp.equals("")) {
                    temp = reader.readLine();
                }
            }
        }
        return list;
    }
}




