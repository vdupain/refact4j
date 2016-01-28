package org.refact4j.xml;

import org.junit.Assert;
import org.junit.Test;
import org.refact4j.util.XmlAssert;

import static org.refact4j.xml.XMLizer.*;

public class XMLizerTest {

    @Test
    public void testXMLizer() {
        XmlAssert.assertXmlEquals(XML.EMPTY_XML, DEFAULT.xml(null));
        Assert.assertEquals(XML.HEADER + "<root/>", DEFAULT.xml(null).toString());
        XmlAssert.assertXmlEquals(XML.HEADER + "<root/>", DEFAULT.xml(null).toString());
    }
}
