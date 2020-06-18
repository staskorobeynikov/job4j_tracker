package ru.job4j.tracker;

import java.util.List;
import java.util.function.Consumer;

public class ShowAllAction implements UserAction {

    @Override
    public String name() {
        return "=== Show all Items ===";
    }

    @Override
    public boolean execute(Input input, ITracker tracker, Consumer<String> output) {
        List<Item> items = tracker.findAll();
        if (items.size() > 0) {
            for (Item item : items) {
                output.accept(String.format("Name: %s| Id: %s",
                        item.getName(), item.getId()));
            }
        } else {
            output.accept("В данный момент хранилище заявок пустое.");
        }
        return true;
    }
}
