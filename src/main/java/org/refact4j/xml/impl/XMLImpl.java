package org.refact4j.xml.impl;

import org.refact4j.xml.ToXmlString;

public class XMLImpl extends AbstractXMLImpl {

    private final String xml;

    private XMLImpl(String xml) {
        this.xml = xml;
    }

    public XMLImpl(ToXmlString toXml) {
        this(toXml != null ? toXml.toXmlString() : ToXmlString.EMPTY_TO_XML.toXmlString());
    }

    public String toXmlString() {
        return this.xml;
    }
}
