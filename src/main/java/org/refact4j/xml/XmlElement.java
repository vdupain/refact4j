package org.refact4j.xml;


public interface XmlElement {

    void onEndElement();

    void onStartElement();

    void setStringValue(String value);

    XmlElement createChildXmlElement(String localName, String qName, XmlAttributes attributes);

}
