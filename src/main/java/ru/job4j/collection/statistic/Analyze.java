package ru.job4j.collection.statistic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Статистика по колекции
 * @author Shegai Evgenii
 * @since 16.09.2021
 * @version 1.0
 */

public class Analyze {

    private int old;
    private int add;
    private int delete;
    private int change;

    public Info diff(List<User> previous, List<User> current) {
        Map<Integer, User> map = new HashMap<>();
        for (User temp : current) {
            map.put(temp.getId(), temp);
        }
        for (User temp : previous) {
            if (map.containsKey(temp.getId()) && map.get(temp.getId()).getName().equals(temp.getName())) {
                old++;
            } else if (map.containsKey(temp.getId()) && !map.get(temp.getId()).getName().equals(temp.getName())) {
                change++;
            } else if (!map.containsKey(temp.getId())) {
                delete++;
            }
        }
        add = current.size() - old - change;
        return new Info(add, change, delete, old);
    }
}
