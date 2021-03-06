package ru.job4j.tracker;

import org.junit.After;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;

public class RefactoringStartUITest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    private final PrintStream stdout = new PrintStream(out);

    private final Consumer<String> output = stdout::println;

    private List<UserAction> actions = List.of(
            new CreateAction(),
            new ShowAllAction(),
            new ReplaceAction(),
            new DeleteAction(),
            new FindByIdAction(),
            new FindByNameAction(),
            new ExitAction()
    );

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    @Test
    public void whenPrtMenu() {
        StubInput input = new StubInput(
                new String[] {"0"}
        );
        StubAction action = new StubAction();
        new StartUI(output).init(input, new Tracker(),
                List.of(action));
        String expect = new StringJoiner(System.lineSeparator(), "",
                System.lineSeparator())
                .add("Menu.")
                .add("0. Stub action")
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
    }

    @Test
    public void whenTestMethodCreateNewItem() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[] {"0", "first Task", "6"});
        new StartUI(output).init(input, tracker, actions);
        List<Item> store = tracker.findByName("first Task");
        assertThat(store.size(), is(1));
        assertThat(store.get(0).getName(), is("first Task"));
    }

    @Test
    public void whenShowAllItems() {
        Tracker tracker = new Tracker();
        Item item = new Item("fix bug");
        tracker.add(item);
        Input input = new StubInput(new String[] {"1", "6"});
        new StartUI(output).init(input, tracker, actions);
        int i1 = new StringBuilder(out.toString()).indexOf("Name:");
        int i2 = new StringBuilder(out.toString()).indexOf("Menu.", i1);
        String actual = new StringBuilder(out.toString()).substring(i1, i2);
        String expected = String.format("Name: %s| Id: %s"
                + System.lineSeparator(), item.getName(), item.getId());
        assertThat(actual, is(expected));
    }

    @Test
    public void whenShowAllItemsWithEmptyStore() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[] {"1", "6"});
        new StartUI(output).init(input, tracker, actions);
        int i1 = new StringBuilder(out.toString()).indexOf("В ");
        int i2 = new StringBuilder(out.toString()).indexOf("Menu.", i1);
        String actual = new StringBuilder(out.toString()).substring(i1, i2);
        String expected = String.format("В данный момент хранилище заявок пустое.%n");
        assertThat(actual, is(expected));
    }

    @Test
    public void whenShowItemReplaceItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        Item item2 = new Item("test2");
        tracker.add(item);
        tracker.add(item2);
        Input input = new StubInput(new String[] {"2", item2.getId(),
                "test2 replace", "6"});
        new StartUI(output).init(input, tracker, actions);
        String actual = tracker.findById(item2.getId()).getName();
        assertThat(actual, is("test2 replace"));
    }

    @Test
    public void whenReplaceItemThanReplaceNoAction() {
        String id = "111111";
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        Item item2 = new Item("test2");
        tracker.add(item);
        tracker.add(item2);
        Input input = new StubInput(new String[] {"2", id,
                "test2 replace", "6"});
        new StartUI(output).init(input, tracker, actions);
        int i1 = new StringBuilder(out.toString()).indexOf("Заявка");
        int i2 = new StringBuilder(out.toString()).indexOf("Menu.", i1);
        String actual = new StringBuilder(out.toString()).substring(i1, i2);
        String expected = String.format("Заявка по id %s не найдена"
                + System.lineSeparator(), id);
        assertThat(actual, is(expected));
    }

    @Test
    public void whenShowItemDelete() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        Item item2 = new Item("test2");
        tracker.add(item);
        tracker.add(item2);
        Input input = new StubInput(new String[] {"3", item2.getId(), "6"});
        new StartUI(output).init(input, tracker, actions);
        Item actual = tracker.findById(item2.getId());
        assertThat(actual, is(nullValue()));
    }

    @Test
    public void whenShowItemThatNotDelete() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        Item item2 = new Item("test2");
        tracker.add(item);
        tracker.add(item2);
        Input input = new StubInput(new String[] {"3", "1111111", "6"});
        new StartUI(output).init(input, tracker, actions);
        Item actual = tracker.findById(item2.getId());
        assertThat(actual.getName(), is("test2"));
    }

    @Test
    public void whenShowItemById() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        Input input = new StubInput(new String[] {"4", item.getId(), "6"});
        new StartUI(output).init(input, tracker, actions);
        int i1 = new StringBuilder(out.toString()).indexOf("Name:");
        int i2 = new StringBuilder(out.toString()).indexOf("Menu.", i1);
        String actual = new StringBuilder(out.toString()).substring(i1, i2);
        String expected = String.format("Name: %s| Id: %s"
                + System.lineSeparator(), item.getName(), item.getId());
        assertThat(actual, is(expected));
    }

    @Test
    public void whenShowItemByIdNotFound() {
        String id = "111111";
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        Input input = new StubInput(new String[] {"4", id, "6"});
        new StartUI(output).init(input, tracker, actions);
        int i1 = new StringBuilder(out.toString()).indexOf("Заявка");
        int i2 = new StringBuilder(out.toString()).indexOf("Menu.", i1);
        String actual = new StringBuilder(out.toString()).substring(i1, i2);
        String expected = String.format("Заявка по id %s не найдена"
                + System.lineSeparator(), id);
        assertThat(actual, is(expected));
    }

    @Test
    public void whenShowItemsFindByName() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        Input input = new StubInput(new String[] {"5", "test1", "6"});
        new StartUI(output).init(input, tracker, actions);
        int i1 = new StringBuilder(out.toString()).indexOf("Name:");
        int i2 = new StringBuilder(out.toString()).indexOf("Menu.", i1);
        String actual = new StringBuilder(out.toString()).substring(i1, i2);
        String expected = String.format("Name: %s| Id: %s"
                + System.lineSeparator(), item.getName(), item.getId());
        assertThat(actual, is(expected));
    }

    @Test
    public void whenShowItemsFindByNameNotFoundItems() {
        String nameItem = "test1";
        String nameFindItem = "test3";
        Tracker tracker = new Tracker();
        Item item = new Item(nameItem);
        tracker.add(item);
        Input input = new StubInput(new String[] {"5", nameFindItem, "6"});
        new StartUI(output).init(input, tracker, actions);
        int i1 = new StringBuilder(out.toString()).indexOf("Заявки");
        int i2 = new StringBuilder(out.toString()).indexOf("Menu.", i1);
        String actual = new StringBuilder(out.toString()).substring(i1, i2);
        String expected = String.format("Заявки по имени: %s не найдены."
                + System.lineSeparator(), nameFindItem);
        assertThat(actual, is(expected));
    }
}
