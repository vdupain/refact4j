package org.refact4j.xml.impl;

import org.refact4j.util.RepositoryImpl;
import org.refact4j.xml.XmlDescriptor;
import org.refact4j.xml.XmlElementFactory;
import org.refact4j.xml.XmlElementFactoryRepository;

public class XmlElementFactoryRepositoryImpl extends RepositoryImpl<String, XmlElementFactory> implements
        XmlElementFactoryRepository {

    public XmlElementFactoryRepositoryImpl() {
    }

    public XmlElementFactory getXmlElementFactory(String xmlTagName) {
        try {
            return this.get(xmlTagName);
        } catch (Exception e) {
            return null;
        }
    }

    public void register(XmlDescriptor xmlDescriptor) {
        xmlDescriptor.getXmlElementFactories()
                .forEach(e-> add(e.getXmlElementTagName(), e));
    }

}
