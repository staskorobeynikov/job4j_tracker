package ru.job4j.tracker.singleton;

import org.junit.Test;
import ru.job4j.tracker.Tracker;

import static org.junit.Assert.*;

public class VersionThreeTest {
    @Test
    public void test() {
        Tracker one = VersionThree.getInstance();
        Tracker two = VersionThree.getInstance();
        assertSame(one, two);
    }
}
