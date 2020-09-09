package ru.job4j.trackerupdate;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SingletonVersionOneTest {
    @Test
    public void test() {
        SingletonVersionOne one = SingletonVersionOne.INSTANCE;
        SingletonVersionOne two = SingletonVersionOne.INSTANCE;
        assertThat(one, is(two));
    }
}