package ru.job4j.trackerupdate;

public enum  SingletonVersionOne {
    INSTANCE;

    private Tracker tracker = new Tracker();

    public Tracker getTracker() {
        return tracker;
    }
}
