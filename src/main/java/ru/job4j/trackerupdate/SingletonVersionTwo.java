package ru.job4j.trackerupdate;

public class SingletonVersionTwo {
    private static SingletonVersionTwo instance;

    private Tracker tracker = new Tracker();

    private SingletonVersionTwo() {
    }

    public static SingletonVersionTwo getInstance() {
        if (instance == null) {
            instance = new SingletonVersionTwo();
        }
        return instance;
    }

    public Tracker getTracker() {
        return tracker;
    }
}
