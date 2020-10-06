package ru.job4j.trackerupdate;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShowByNameActionTest {
    @Test
    public void execute() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        tracker.add(new Item("First item"));
        Item item = tracker.add(new Item("Second item"));

        Input input = mock(Input.class);
        ShowByNameAction nameAction = new ShowByNameAction(out);

        when(input.askStr(any(String.class))).thenReturn(item.getName());

        nameAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find item By Name ===" + ln + item + ln));
    }
}