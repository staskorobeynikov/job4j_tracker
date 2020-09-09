package ru.job4j.trackerupdate;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SingletonVersionFourTest {
    @Test
    public void test() {
        SingletonVersionFour four = SingletonVersionFour.getInstance();
        SingletonVersionFour one = SingletonVersionFour.getInstance();
        assertThat(four, is(one));
    }
}