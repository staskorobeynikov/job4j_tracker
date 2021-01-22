package ru.job4j.tracker.singleton;

import ru.job4j.tracker.Tracker;

public enum VersionOne {
    INSTANCE;

    private Tracker tracker = new Tracker();

    public Tracker getTracker() {
        return tracker;
    }
}
