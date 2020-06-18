package ru.job4j.tracker;

import java.util.List;
import java.util.function.Consumer;

public class StartUI {

    private final Consumer<String> output;

    public StartUI(Consumer<String> output) {
        this.output = output;
    }

    public void init(Input input, ITracker tracker, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select: ", actions.size());
            UserAction action = actions.get(select);
            run = action.execute(input, tracker, output);
        }
    }
    private void showMenu(List<UserAction> actions) {
        output.accept("Menu.");
        for (int index = 0; index < actions.size(); index++) {
            output.accept(index + ". " + actions.get(index).name());
        }
    }
    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Input validate = new ValidateInput(input);
        Tracker tracker = new Tracker();
        List<UserAction> actions = List.of(
                new CreateAction(),
                new ShowAllAction(),
                new ReplaceAction(),
                new DeleteAction(),
                new FindByIdAction(),
                new FindByNameAction(),
                new ExitAction()

        );
        new StartUI(System.out::println).init(validate, tracker, actions);
    }
}
