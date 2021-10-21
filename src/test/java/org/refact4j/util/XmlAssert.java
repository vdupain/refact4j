package org.refact4j.util;

import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.ElementNameAndAttributeQualifier;
import org.custommonkey.xmlunit.XMLUnit;
import org.refact4j.xml.ToXmlString;
import org.xmlunit.assertj3.CompareAssert;

public class XmlAssert extends org.junit.Assert {

    static {
        XMLUnit.setIgnoreWhitespace(true);
        XMLUnit.setNormalizeWhitespace(true);
    }

    public static void assertXmlEquals(String expected, String actual) {
        org.xmlunit.assertj3.XmlAssert.assertThat(actual).and(expected).ignoreWhitespace().areIdentical();
        /*
        try {
            Diff diff = new Diff(expected, actual);
            if (!diff.identical()) {
                assertEquals(expected, actual);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
         */
    }

    public static void assertXmlEquivalent(String expected, String actual) {
        try {
            Diff diff = new Diff(expected, actual);
            DetailedDiff detailedDiff = new DetailedDiff(diff);
            detailedDiff.overrideElementQualifier(new ElementNameAndAttributeQualifier());
            if (!detailedDiff.similar()) {
                assertEquals(expected, actual);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
