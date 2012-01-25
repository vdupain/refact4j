package org.refact4j.xml.impl.stax;

import org.refact4j.xml.XmlAttributes;

import javax.xml.stream.XMLStreamReader;

public class StaxAttributes implements XmlAttributes {

    private final XMLStreamReader reader;

    public StaxAttributes(XMLStreamReader reader) {
        this.reader = reader;
    }

    public int getIndex(String uri, String localName) {
        return 0;
    }

    public int getIndex(String name) {
        return 0;
    }

    public int getLength() {
        return this.reader.getAttributeCount();
    }

    public String getLocalName(int index) {
        return this.reader.getAttributeLocalName(index);
    }

    public String getQName(int index) {
        return null;
    }

    public String getType(int index) {
        return this.reader.getAttributeType(index);
    }

    public String getType(String uri, String localName) {
        return null;
    }

    public String getType(String name) {
        return null;
    }

    public String getURI(int index) {
        this.reader.getAttributeNamespace(index);
        return null;
    }

    public String getValue(int index) {
        return this.reader.getAttributeValue(index);
    }

    public String getValue(String uri, String localName) {
        return this.reader.getAttributeValue(uri, localName);
    }

    public String getValue(String name) {
        return null;
    }

}
