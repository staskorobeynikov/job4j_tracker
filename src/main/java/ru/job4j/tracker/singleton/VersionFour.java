package ru.job4j.tracker.singleton;

import ru.job4j.tracker.Tracker;

public class VersionFour {

    private VersionFour() {
    }

    public static Tracker getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder {
        private static final Tracker INSTANCE = new Tracker();
    }
}
