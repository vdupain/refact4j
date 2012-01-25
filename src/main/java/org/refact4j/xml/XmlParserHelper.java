package org.refact4j.xml;

import org.apache.xerces.parsers.SAXParser;
import org.refact4j.xml.impl.sax.SaxXmlParser;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.Reader;

public final class XmlParserHelper {

    public static void parse(XMLReader xmlReader, InputSource is, XmlElement xmlRootElement) throws Exception {
        parse(xmlReader, is, xmlRootElement, null);
    }

    private static void parse(XMLReader xmlReader, InputSource is, XmlElement xmlRootElement,
                              EntityResolver entityResolver) throws Exception {
        SaxXmlParser xmlParser = new SaxXmlParser();
        xmlParser.setXmlReader(xmlReader);
        xmlParser.setXmlRootElement(xmlRootElement);
        xmlParser.parse(is, entityResolver);
    }

    public static void parse(Reader reader, XmlElement xmlRootElement) throws Exception {
        parse(new SAXParser(), new InputSource(reader), xmlRootElement);
    }

}
