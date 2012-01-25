package org.refact4j.xml.impl.sax;

import org.refact4j.xml.XmlAttributes;
import org.xml.sax.Attributes;

public class SaxAttributes implements XmlAttributes {

    private final Attributes attributes;

    public SaxAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public int getIndex(String uri, String localName) {
        return attributes.getIndex(uri, localName);
    }

    public int getIndex(String name) {
        return attributes.getIndex(name);
    }

    public int getLength() {
        return attributes.getLength();
    }

    public String getLocalName(int index) {
        return attributes.getLocalName(index);
    }

    public String getQName(int index) {
        return attributes.getQName(index);
    }

    public String getType(int index) {
        return attributes.getType(index);
    }

    public String getType(String uri, String localName) {
        return attributes.getType(uri, localName);
    }

    public String getType(String name) {
        return attributes.getType(name);
    }

    public String getURI(int index) {
        return attributes.getURI(index);
    }

    public String getValue(int index) {
        return attributes.getValue(index);
    }

    public String getValue(String uri, String localName) {
        return attributes.getValue(uri, localName);
    }

    public String getValue(String name) {
        return attributes.getValue(name);
    }

    public Attributes getSaxAttributes() {
        return this.attributes;
    }
}
