package org.refact4j.xml.impl;

import org.refact4j.util.RepositoryImpl;
import org.refact4j.xml.XmlDescriptor;
import org.refact4j.xml.XmlElementFactory;
import org.refact4j.xml.XmlElementFactoryRepository;

public class XmlElementFactoryRepositoryImpl extends RepositoryImpl<String, XmlElementFactory> implements
        XmlElementFactoryRepository {

    public XmlElementFactoryRepositoryImpl() {
        setKeyifier(xmlElementFactory -> xmlElementFactory.getXmlElementTagName());
    }

    public XmlElementFactory getXmlElementFactory(String xmlTagName) {
        try {
            return this.get(xmlTagName);
        } catch (Exception e) {
            return null;
        }
    }

    public void register(XmlDescriptor xmlDescriptor) {
        for (XmlElementFactory factory : xmlDescriptor.getXmlElementFactories()) {
            add(factory);
        }
    }

}
