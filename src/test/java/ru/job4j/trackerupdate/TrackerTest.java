package ru.job4j.trackerupdate;

import org.junit.After;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

public class TrackerTest {

    @After
    public void tearDown() {
        Tracker.getInstance().clear();
    }

    @Test
    public void whenTestMethodFindById() {
        Tracker tracker = Tracker.getInstance();
        Item item = new Item("test");
        tracker.add(item);
        Item byId = tracker.findById(item.getId());
        assertThat(byId.getName(), is("test"));
    }

    @Test
    public void whenTestMethodFindAll() {
        Tracker tracker = Tracker.getInstance();
        tracker.add(new Item("test"));
        tracker.add(new Item("test1"));
        tracker.add(new Item("test2"));
        List<Item> findAll = tracker.findAll();
        assertThat(findAll.size(), is(3));
        assertThat(findAll.get(2).getName(), is("test2"));
    }

    @Test
    public void whenTestMethodFindByName() {
        Tracker tracker = Tracker.getInstance();
        tracker.add(new Item("test"));
        tracker.add(new Item("test1"));
        tracker.add(new Item("test2"));
        List<Item> findName = tracker.findByName("test2");
        assertThat(findName.get(0).getName(), is("test2"));
    }

    @Test
    public void whenReplace() {
        Tracker tracker = Tracker.getInstance();
        Item bug = new Item("Bug");
        tracker.add(bug);
        int id = bug.getId();
        Item bugWithDesc = new Item("Bug with description");
        tracker.replace(id, bugWithDesc);
        assertThat(tracker.findById(id).getName(), is("Bug with description"));
    }

    @Test
    public void whenDelete() {
        Tracker tracker = Tracker.getInstance();
        Item bug = new Item("Bug");
        tracker.add(bug);
        int id = bug.getId();
        tracker.delete(id);
        assertThat(tracker.findById(id), is(nullValue()));
    }
}
