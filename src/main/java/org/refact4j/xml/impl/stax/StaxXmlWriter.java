package org.refact4j.xml.impl.stax;

import org.refact4j.xml.XmlWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;


public class StaxXmlWriter implements XmlWriter {
    private final XMLStreamWriter xmlStreamWriter;

    public StaxXmlWriter(XMLStreamWriter xmlStreamWriter) {
        this.xmlStreamWriter = xmlStreamWriter;
    }

    public void writeAttribute(String localName, String value) {
        try {
            if (value != null) {
                this.xmlStreamWriter.writeAttribute(localName, value);
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void writeCharacters(String text) {
        try {
            this.xmlStreamWriter.writeCharacters(text);
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeStartElement(String localName) {
        try {
            this.xmlStreamWriter.writeStartElement(localName);
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeEndElement() {
        try {
            this.xmlStreamWriter.writeEndElement();
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }
}
