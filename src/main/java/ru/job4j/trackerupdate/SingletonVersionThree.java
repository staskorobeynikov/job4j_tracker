package ru.job4j.trackerupdate;

public class SingletonVersionThree {
    private static final SingletonVersionThree INSTANCE = new SingletonVersionThree();

    private Tracker tracker = new Tracker();

    private SingletonVersionThree() {
    }

    public static SingletonVersionThree getInstance() {
        return INSTANCE;
    }

    public Tracker getTracker() {
        return tracker;
    }
}
