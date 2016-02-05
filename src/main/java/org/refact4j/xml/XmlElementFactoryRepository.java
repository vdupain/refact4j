package org.refact4j.xml;

import java.util.Map;

public interface XmlElementFactoryRepository extends Map<String, XmlElementFactory> {

    void register(XmlDescriptor xmlDescriptor);

    XmlElementFactory getXmlElementFactory(String xmlTagName);
}
