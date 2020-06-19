package ru.job4j.trackerupdate;

public class ShowByNameAction implements UserAction {

    private final Output output;

    public ShowByNameAction(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "Find item By Name";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        output.println("=== Find item By Name ===");
        String name = input.askStr("Enter name: ");
        Item[] items = tracker.findByName(name);
        if (items.length > 0) {
            for (int i = 0; i < items.length; i++) {
                output.println(items[i]);
            }
        } else {
            output.println(String.format("Item with name=%s not found.", name));
        }
        return true;
    }
}
