package ru.job4j.tracker.singleton;

import org.junit.Test;
import ru.job4j.tracker.Tracker;

import static org.junit.Assert.*;

public class VersionFourTest {
    @Test
    public void test() {
        Tracker one = VersionFour.getInstance();
        Tracker two = VersionFour.getInstance();
        assertSame(one, two);
    }
}
