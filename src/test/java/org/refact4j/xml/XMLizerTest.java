package org.refact4j.xml;

import org.junit.Assert;
import org.junit.Test;
import org.refact4j.util.XmlAssert;

public class XMLizerTest {

    @Test
    public void testXMLizer() {
        XmlAssert.assertXmlEquals(XML.EMPTY_XML, AbstractXMLizer.DEFAULT.xml(null));
        Assert.assertEquals(XML.HEADER + "<root/>", AbstractXMLizer.DEFAULT.xml(null).toString());
        XmlAssert.assertXmlEquals(XML.HEADER + "<root/>", AbstractXMLizer.DEFAULT.xml(null).toString());
        //<?xml version='1.0' encoding='utf-8'?>
    }
}
