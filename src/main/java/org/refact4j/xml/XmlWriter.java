package org.refact4j.xml;


public interface XmlWriter {

    void writeAttribute(String localName, String value);

    void writeCharacters(String text);

    void writeStartElement(String localName);

    void writeEndElement();
}
