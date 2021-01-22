package ru.job4j.tracker.singleton;

import ru.job4j.tracker.Tracker;

public class VersionTwo {

    private static Tracker instance;

    private VersionTwo() {
    }

    public static Tracker getInstance() {
        if (instance == null) {
            instance = new Tracker();
        }
        return instance;
    }
}
