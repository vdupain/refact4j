package org.refact4j.xml;

import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractXmlReaderTest {
    final String xmlWithNameSpace =
            "<xmlRoot xmlns='http://xml.refact4j.org' " +
                    "   xmlns:dummy='http://dummy.xml.refact4j.org'" +
                    "   xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'" +
                    "   xsi:schemaLocation='http://xml.refact4j.org http://xml.refact4j.org" +
                    "   http://dummy.xml.refact4j.org http://dummy.xml.refact4j.org'>" +
                    "   <a param1='value1' param2='value2'>abcd</a>" +
                    "   <dummy:b>" +
                    "       <c p1='v1'>123abc</c>" +
                    "   </dummy:b>" +
                    "</xmlRoot>";

    final DummyXmlElement xmlRootElement = new DummyXmlElement();

    protected abstract void readXml() throws Exception;

    @Test
    public void testXmlReader() throws Exception {
        readXml();
        DummyXmlElement rootXmlElement = xmlRootElement.xmlElements.get(0);
        DummyXmlElement aXmlElement = rootXmlElement.xmlElements.get(0);
        DummyXmlElement bXmlElement = rootXmlElement.xmlElements.get(1);
        DummyXmlElement cXmlElement = bXmlElement.xmlElements.get(0);

        Assert.assertEquals("xmlRoot", rootXmlElement.localName);
        Assert.assertEquals(rootXmlElement.localName, rootXmlElement.qName);
        Assert.assertEquals("[schemaLocation='http://xml.refact4j.org http://xml.refact4j.org   http://dummy.xml.refact4j.org http://dummy.xml.refact4j.org']", rootXmlElement.sAttributes);
        Assert.assertEquals("", rootXmlElement.value.trim());

        Assert.assertEquals("a", aXmlElement.localName);
        Assert.assertEquals("[param1='value1'][param2='value2']", aXmlElement.sAttributes);
        Assert.assertEquals("abcd", aXmlElement.value);

        Assert.assertEquals("b", bXmlElement.localName);
        Assert.assertEquals("dummy:b", bXmlElement.qName);
        Assert.assertEquals("", bXmlElement.sAttributes);
        Assert.assertEquals("", bXmlElement.value.trim());

        Assert.assertEquals("c", cXmlElement.localName);
        Assert.assertEquals(cXmlElement.localName, cXmlElement.qName);
        Assert.assertEquals("[p1='v1']", cXmlElement.sAttributes);
        Assert.assertEquals("123abc", cXmlElement.value);
    }

}
