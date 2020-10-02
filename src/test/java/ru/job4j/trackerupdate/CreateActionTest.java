package ru.job4j.trackerupdate;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreateActionTest {
    @Test
    public void execute() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();

        Input input = mock(Input.class);
        CreateAction create = new CreateAction(out);

        when(input.askStr(any(String.class))).thenReturn("New item name");

        create.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Create a new Item ===" + ln));
        assertThat(tracker.findAll().get(0).getName(), is("New item name"));
    }
}