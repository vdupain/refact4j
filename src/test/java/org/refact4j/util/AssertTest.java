package org.refact4j.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
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
            String expected = "Expected that: ( NOT (x=1))" + StringHelper.LINE_SEPARATOR;
            expected += "but x was : 1" + StringHelper.LINE_SEPARATOR;
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
            String expected = "Expected that: (x INSTANCEOF class java.lang.Integer)" + StringHelper.LINE_SEPARATOR;
            expected += "but x was : abc" + StringHelper.LINE_SEPARATOR;
            assertEquals(expected, e.getMessage());
        }

        assertThat(5, between(0, 10));
        assertThat(12, not(between(0, 10)));
        assertThat(5, between(0, 10).and(equalTo(5)));
        assertThat(5, between(0, 10).and(not(equalTo(1))));
        assertThat(5, between(0, 10).and(not(equalTo(1))).and(isNotNull()));
        assertThat(5, and(between(0, 10).and(not(equalTo(1))), isNotNull()));
        assertThat(5, or(equalTo(5), equalTo(6)));

        try {
            assertThat(10, between(1, 11).and(not(equalTo(10))));
            fail();
        } catch (Throwable e) {
            String expected = "Expected that: ((x BETWEEN 1 AND 11) AND ( NOT (x=10)))" + StringHelper.LINE_SEPARATOR;
            expected += "but x was : 10" + StringHelper.LINE_SEPARATOR;
            assertEquals(expected, e.getMessage());
        }
    }

    @Test
    public void testAssertThatSomethingMatchesNullNotNullConstraint() {
        assertThat(1, isNotNull());
        assertThat("abc", isNotNull());
        assertThat(null, isNull());
        try {
            assertThat(null, isNotNull());
            fail();
        } catch (Throwable e) {
            String expected = "Expected that: ( IS NOT NULL x)" + StringHelper.LINE_SEPARATOR;
            expected += "but x was : null" + StringHelper.LINE_SEPARATOR;
            assertEquals(expected, e.getMessage());
        }
    }

}
