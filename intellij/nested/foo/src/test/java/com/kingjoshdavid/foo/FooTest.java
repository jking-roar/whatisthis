package com.kingjoshdavid.foo;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FooTest {
    @Test
    public void fooFoos() {
        String result = Foo.foo();
        assertThat(result, is("Foo"));
    }
}