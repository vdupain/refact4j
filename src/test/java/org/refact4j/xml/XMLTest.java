package org.refact4j.xml;

import org.junit.Assert;
import org.junit.Test;
import org.refact4j.util.XmlAssert;


public class XMLTest {

    @Test
    public void testDefaultXmlConverter() {
        XmlAssert.assertXmlEquals(XML.EMPTY_XML.toString(), XML.DEFAULT.convert(null).toString());
        Assert.assertEquals(XML.HEADER + "<root/>", XML.DEFAULT.convert(null).toString());
        XmlAssert.assertXmlEquals(XML.HEADER + "<root/>", XML.DEFAULT.convert(null).toString());
    }
}
