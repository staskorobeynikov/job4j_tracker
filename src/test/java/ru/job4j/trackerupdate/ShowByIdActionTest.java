package ru.job4j.trackerupdate;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShowByIdActionTest {
    @Test
    public void execute() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("First item"));
        tracker.add(new Item("Second item"));

        Input input = mock(Input.class);
        ShowByIdAction idAction = new ShowByIdAction(out);

        when(input.askInt(any(String.class))).thenReturn(item.getId());

        idAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find Item By ID ===" + ln + item + ln));
    }
}