package com.kingjoshdavid.bar;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BarTest {

    @Test
    public void barBars() {
        Bar subject = new Bar();
        String result = subject.bar();
        assertThat(result, is("Bar"));
    }
}