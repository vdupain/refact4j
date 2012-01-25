package org.refact4j.xml.impl.sax;

import org.refact4j.xml.XmlElement;
import org.refact4j.xml.XmlReaderHandler;
import org.refact4j.xml.impl.DefaultXmlReaderHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class SaxHandler extends DefaultHandler {
    private final XmlReaderHandler xmlReaderHandler = new DefaultXmlReaderHandler();

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        xmlReaderHandler.endElement(namespaceURI, localName, qName);
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes attributes)
            throws SAXException {
        xmlReaderHandler.startElement(namespaceURI, localName, qName, new SaxAttributes(attributes));
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        xmlReaderHandler.characters(ch, start, length);
    }

    public void setXmlRootElement(XmlElement xmlRootElement) {
        xmlReaderHandler.setXmlRootElement(xmlRootElement);
    }

}
