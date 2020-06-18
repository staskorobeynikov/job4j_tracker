package ru.job4j.trackerupdate;

public class StartUI {

    public void init(Input input, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu();
            int select = input.askInt("Select: ");
            if (select == 0) {
                StartUI.createItem(input, tracker);
            } else if (select == 1) {
                StartUI.showAllItems(tracker);
            } else if (select == 2) {
                StartUI.editItem(input, tracker);
            } else if (select == 3) {
                StartUI.deleteItem(input, tracker);
            } else if (select == 4) {
                StartUI.showItemById(input, tracker);
            } else if (select == 5) {
                StartUI.showItemsByName(input, tracker);
            } else if (select == 6) {
                run = false;
            }
        }
    }

    public static void showItemsByName(Input input, Tracker tracker) {
        System.out.println("=== Find item By Name ===");
        String name = input.askStr("Enter name: ");
        Item[] items = tracker.findByName(name);
        if (items.length > 0) {
            for (int i = 0; i < items.length; i++) {
                System.out.println(items[i]);
            }
        } else {
            System.out.println(String.format("Item with name=%s not found.", name));
        }
    }

    public static void showItemById(Input input, Tracker tracker) {
        System.out.println("=== Find Item By ID ===");
        int id = input.askInt("Enter id: ");
        Item item = tracker.findById(id);
        if (item != null) {
            System.out.println(item);
        } else {
            System.out.println(String.format("Item with id=%s not found.", id));
        }
    }

    public static void deleteItem(Input input, Tracker tracker) {
        System.out.println("=== Delete Item ===");
        int id = input.askInt("Enter id: ");
        if (tracker.delete(id)) {
            System.out.println("Item was deleted.");
        } else {
            System.out.println(String.format("Item with id=%s not found.", id));
        }
    }

    public static void editItem(Input input, Tracker tracker) {
        System.out.println("=== Edit item ===");
        int id = input.askInt("Enter id: ");
        String name = input.askStr("Enter new name for Item: ");
        Item item = new Item(name);
        if (tracker.replace(id, item)) {
            System.out.println("Edit item is done.");
        } else {
            System.out.println(String.format("Item with id=%s not found.", id));
        }
    }

    public static void showAllItems(Tracker tracker) {
        System.out.println("=== Show All Items ===");
        Item[] items = tracker.findAll();
        if (items.length > 0) {
            for (int i = 0; i < items.length; i++) {
                System.out.println(items[i]);
            }
        } else {
            System.out.println("Tracker not contains items.");
        }
    }

    public static void createItem(Input input, Tracker tracker) {
        System.out.println("=== Create a new Item ====");
        String name = input.askStr("Enter name: ");
        Item item = new Item(name);
        tracker.add(item);
    }

    private void showMenu() {
        System.out.println("Menu.");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        new StartUI().init(input, tracker);
    }
}
