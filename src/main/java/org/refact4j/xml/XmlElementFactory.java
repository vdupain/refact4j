package org.refact4j.xml;

import org.refact4j.xml.reader.DefaultXmlElementReader;

public interface XmlElementFactory {

    String getXmlElementTagName();

    XmlElement createXmlElement(DefaultXmlElementReader xmlElement);


}
