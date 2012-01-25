package org.refact4j.xml;

public interface XmlReaderHandler {

    void endElement(String namespaceURI, String localName, String qName);

    void startElement(String namespaceURI, String localName, String qName, XmlAttributes attributes);

    void characters(char[] ch, int start, int length);

    void setXmlRootElement(XmlElement xmlRootElement);

}
