package org.refact4j.xml;

import org.refact4j.util.Repository;

public interface XmlElementFactoryRepository extends Repository<String, XmlElementFactory> {

    void register(XmlDescriptor xmlDescriptor);

    XmlElementFactory getXmlElementFactory(String xmlTagName);
}
