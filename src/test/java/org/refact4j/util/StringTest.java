package org.refact4j.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringTest {

    @Test
    public void testToUpperCaseWithUnderscores() {
        assertEquals("AZERTY_QWERTY", StringHelper
                .toUpperCaseWithUnderscores("azertyQwerty"));
        assertEquals("AZERTY_QWERTY", StringHelper
                .toUpperCaseWithUnderscores("azerty_qwerty"));
        assertEquals("AZERTY_012QWERTY", StringHelper
                .toUpperCaseWithUnderscores("azerty012qwerty"));
    }

    @Test
    public void testGetStringFromFile() throws Exception {
        String s = StringHelper.getStringFromResourceFile("/org/refact4j/file.txt");
        assertEquals("abcdef", s);
    }

    @Test
    public void testReplace() {
        assertEquals("abc (def", StringHelper.replace("abc(def", "(", " ("));
    }

    @Test
    public void testCapitalize() {
        assertEquals("Abc", StringHelper.capitalize("abc"));
        assertEquals("ABC", StringHelper.capitalize("ABC"));
        assertEquals("123", StringHelper.capitalize("123"));
    }

    @Test
    public void testUncapitalize() {
        assertEquals("abc", StringHelper.uncapitalize("Abc"));
        assertEquals("aBC", StringHelper.uncapitalize("ABC"));
        assertEquals("123", StringHelper.uncapitalize("123"));
    }

}
