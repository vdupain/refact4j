package org.refact4j.xml;

import java.util.ArrayList;
import java.util.List;

public class DummyXmlElement implements XmlElement {
    String localName;
    String value;
    String qName;
    String sAttributes = "";
    List<DummyXmlElement> xmlElements = new ArrayList<DummyXmlElement>();

    public XmlElement createChildXmlElement(String localName, String qName, XmlAttributes attributes) {
        DummyXmlElement xmlElement = new DummyXmlElement();
        xmlElement.localName = localName;
        xmlElement.qName = qName;
        for (int i = 0; i < attributes.getLength(); i++) {
            xmlElement.sAttributes += "[" + attributes.getLocalName(i) + "='" + attributes.getValue(i) + "']";
        }
        xmlElements.add(xmlElement);
        return xmlElement;
    }

    public void onEndElement() {
    }

    public void onStartElement() {
    }

    public void setStringValue(String value) {
        this.value = value;
    }

}

