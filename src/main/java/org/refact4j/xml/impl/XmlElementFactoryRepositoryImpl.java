package org.refact4j.xml.impl;

import org.refact4j.xml.XmlDescriptor;
import org.refact4j.xml.XmlElementFactory;
import org.refact4j.xml.XmlElementFactoryRepository;

import java.util.HashMap;

public class XmlElementFactoryRepositoryImpl extends HashMap<String, XmlElementFactory> implements
        XmlElementFactoryRepository {

    public XmlElementFactoryRepositoryImpl() {
    }

    public XmlElementFactory getXmlElementFactory(String xmlTagName) {
        return this.get(xmlTagName);
    }

    public void register(XmlDescriptor xmlDescriptor) {
        xmlDescriptor.getXmlElementFactories()
                .forEach(e -> putIfAbsent(e.getXmlElementTagName(), e));
    }

}
