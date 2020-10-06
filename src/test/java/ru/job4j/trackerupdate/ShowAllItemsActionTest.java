package ru.job4j.trackerupdate;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class ShowAllItemsActionTest {
    @Test
    public void execute() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("First item"));
        Item item1 = tracker.add(new Item("Second item"));

        Input input = mock(Input.class);
        ShowAllItemsAction nameAction = new ShowAllItemsAction(out);

        nameAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Show All Items ===" + ln
                + item + ln
                + item1 + ln)
        );
    }
}