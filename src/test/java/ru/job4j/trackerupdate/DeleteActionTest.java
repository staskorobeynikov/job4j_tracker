package ru.job4j.trackerupdate;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeleteActionTest {
    @Test
    public void execute() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("New item name"));

        Input input = mock(Input.class);
        DeleteAction delete = new DeleteAction(out);

        when(input.askInt(any(String.class))).thenReturn(1);

        delete.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Delete Item ===" + ln + "Item was deleted." + ln));
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }
}