package org.refact4j.util;

import org.junit.Test;

import static org.refact4j.util.Assert.*;

public class AssertTest {

    @Test
    public void testAssertThatSomethingMatchesConstraint() {
        assertThat(1, equalTo(1));
        assertThat(1, notEqualTo(2));
        assertThat(1, instanceOf(Integer.class));
        try {
            assertThat(1, notEqualTo(1));
            fail();
        } catch (Throwable e) {
            String expected = "Expected that: ( NOT (value=1))" + StringHelper.LINE_SEPARATOR;
            expected += "but value was : 1" + StringHelper.LINE_SEPARATOR;
            assertEquals(expected, e.getMessage());
        }

        assertThat("abc", equalTo("abc"));
        assertThat("abc", notEqualTo("xxx"));
        assertThat("abc", matchRegEx("ab?"));
        assertThat("abc", instanceOf(String.class));
        assertThat("abc", equalTo("abc").and(notEqualTo("xxx")).and(matchRegEx("ab?")).and(instanceOf(String.class)));

        try {
            assertThat("abc", instanceOf(Integer.class));
            fail();
        } catch (Throwable e) {
            String expected = "Expected that: (value INSTANCEOF class java.lang.Integer)" + StringHelper.LINE_SEPARATOR;
            expected += "but value was : abc" + StringHelper.LINE_SEPARATOR;
            assertEquals(expected, e.getMessage());
        }

        assertThat(5, between(0, 10));
        assertThat(12, not(between(0, 10)));
        assertThat(5, between(0, 10).and(equalTo(5)));
        assertThat(5, between(0, 10).and(not(equalTo(1))));
        assertThat(5, between(0, 10).and(not(equalTo(1))).and(not(isNull())));
        assertThat(5, and(between(0, 10).and(not(equalTo(1))),not(isNull())));
        assertThat(5, or(equalTo(5), equalTo(6)));

        try {
            assertThat(10, between(1, 11).and(not(equalTo(10))));
            fail();
        } catch (Throwable e) {
            String expected = "Expected that: ((value BETWEEN 1 AND 11) AND ( NOT (value=10)))" + StringHelper.LINE_SEPARATOR;
            expected += "but value was : 10" + StringHelper.LINE_SEPARATOR;
            assertEquals(expected, e.getMessage());
        }
    }

    @Test
    public void testAssertThatSomethingMatchesNullNotNullConstraint() {
        assertThat(1, not(isNull()));
        assertThat("abc", not(isNull()));
        assertThat(null, isNull());
        try {
            assertThat(null, not(isNull()));
            fail();
        } catch (Throwable e) {
            String expected = "Expected that: ( NOT ( IS NULL value))" + StringHelper.LINE_SEPARATOR;
            expected += "but value was : null" + StringHelper.LINE_SEPARATOR;
            assertEquals(expected, e.getMessage());
        }
    }

}
