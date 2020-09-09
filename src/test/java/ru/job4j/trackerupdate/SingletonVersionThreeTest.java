package ru.job4j.trackerupdate;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SingletonVersionThreeTest {
    @Test
    public void test() {
        SingletonVersionThree one = SingletonVersionThree.getInstance();
        SingletonVersionThree two = SingletonVersionThree.getInstance();
        assertThat(one, is(two));
    }
}