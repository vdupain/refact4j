package org.refact4j.xml;

public class XmlAttributeNotFoundException extends RuntimeException {

    public XmlAttributeNotFoundException(final String xmlAttrName) {
        super(xmlAttrName);
    }

}
