package ru.job4j.trackerupdate;

public class SingletonVersionFour {
    private Tracker tracker = new Tracker();

    private SingletonVersionFour() {
    }

    public static SingletonVersionFour getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder {
        private static final SingletonVersionFour INSTANCE = new SingletonVersionFour();
    }

    public Tracker getTracker() {
        return tracker;
    }
}
