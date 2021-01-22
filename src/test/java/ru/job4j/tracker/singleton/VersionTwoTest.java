package ru.job4j.tracker.singleton;

import org.junit.Test;
import ru.job4j.tracker.Tracker;

import static org.junit.Assert.*;

public class VersionTwoTest {
    @Test
    public void test() {
        Tracker one = VersionTwo.getInstance();
        Tracker two = VersionTwo.getInstance();
        assertSame(one, two);
    }
}
