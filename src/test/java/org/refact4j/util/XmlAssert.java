package org.refact4j.util;

import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.ElementNameAndAttributeQualifier;
import org.custommonkey.xmlunit.XMLUnit;
import org.refact4j.xml.ToXmlString;
import org.refact4j.xml.XML;

public class XmlAssert extends Assert {

	static {
		XMLUnit.setIgnoreWhitespace(true);
		XMLUnit.setNormalizeWhitespace(true);
	}

	public static void assertXmlEquivalent(XML expected, XML actual) {
		assertXmlEquivalent(expected, actual);
	}

	public static void assertXmlEquals(ToXmlString expected, ToXmlString actual) {
		if (expected == null && actual == null)
			return;
		if (expected != null && expected.equals(actual))
			return;
		assertXmlEquals(expected.toXmlString(), actual.toXmlString());
	}

	public static void assertXmlEquivalent(ToXmlString expected, ToXmlString actual) {
		if (expected == null && actual == null)
			return;
		if (expected != null && expected.equals(actual))
			return;
		assertXmlEquivalent(expected.toXmlString(), actual.toXmlString());
	}

	public static void assertXmlEquals(String expected, String actual) {
		try {
			Diff diff = new Diff(expected, actual);
			if (!diff.identical()) {
				assertEquals(expected, actual);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
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
