package ru.job4j.tracker.singleton;

import ru.job4j.tracker.Tracker;

public class VersionThree {

    private static final Tracker INSTANCE = new Tracker();

    private VersionThree() {
    }

    public static Tracker getInstance() {
        return INSTANCE;
    }
}
