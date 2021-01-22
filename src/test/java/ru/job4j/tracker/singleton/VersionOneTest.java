package ru.job4j.tracker.singleton;

import org.junit.Test;
import ru.job4j.tracker.Tracker;

import static org.junit.Assert.*;

public class VersionOneTest {
    @Test
    public void test() {
        Tracker one = VersionOne.INSTANCE.getTracker();
        Tracker two = VersionOne.INSTANCE.getTracker();
        assertSame(one, two);
    }
}
