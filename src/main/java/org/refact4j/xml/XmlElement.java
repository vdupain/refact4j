package org.refact4j.xml;


public interface XmlElement {

    default void onEndElement() {
        // noop
    }

    default void onStartElement() {
        // noop
    }

    default void setStringValue(String value) {
        // noop
    }

    XmlElement createChildXmlElement(String localName, String qName, XmlAttributes attributes);

}
