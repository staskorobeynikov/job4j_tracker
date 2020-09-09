package ru.job4j.trackerupdate;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SingletonVersionTwoTest {
    @Test
    public void test() {
        SingletonVersionTwo one = SingletonVersionTwo.getInstance();
        SingletonVersionTwo two = SingletonVersionTwo.getInstance();
        assertThat(one, is(two));
    }
}