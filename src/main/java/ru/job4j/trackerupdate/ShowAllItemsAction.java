package ru.job4j.trackerupdate;

public class ShowAllItemsAction implements UserAction {

    private final Output output;

    public ShowAllItemsAction(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "Show All Items";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        output.println("=== Show All Items ===");
        Item[] items = tracker.findAll();
        if (items.length > 0) {
            for (int i = 0; i < items.length; i++) {
                output.println(items[i]);
            }
        } else {
            output.println("Tracker not contains items.");
        }
        return true;
    }
}
