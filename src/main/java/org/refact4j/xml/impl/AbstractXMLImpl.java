package org.refact4j.xml.impl;

import org.refact4j.xml.XML;

public abstract class AbstractXMLImpl implements XML {

    public abstract String toXmlString();

    public String toString() {
        return this.toXmlString();
    }
}