package ru.job4j.collection.statistic;

/**
 * Статистика по колекции
 * @author Shegai Evgenii
 * @since 16.09.2021
 * @version 1.0
 */

public class Info {

    private int added;
    private int changed;
    private int deleted;
    private int old;

    public Info(int added, int changed, int deleted, int old) {
        this.added = added;
        this.changed = changed;
        this.deleted = deleted;
        this.old = old;
    }

    public int getAdded() {
        return added;
    }

    public int getChanged() {
        return changed;
    }

    public int getDeleted() {
        return deleted;
    }

    public int getOld() {
        return old;
    }
}
