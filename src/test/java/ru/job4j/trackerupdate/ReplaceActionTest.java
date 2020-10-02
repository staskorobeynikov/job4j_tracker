package ru.job4j.trackerupdate;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReplaceActionTest {
    @Test
    public void execute() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";

        Input input = mock(Input.class);
        ReplaceAction rep = new ReplaceAction(out);

        when(input.askInt(any(String.class))).thenReturn(1);
        when(input.askStr(any(String.class))).thenReturn(replacedName);

        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Edit item ===" + ln + "Edit item is done." + ln));
        assertThat(tracker.findAll().get(0).getName(), is(replacedName));
    }
}