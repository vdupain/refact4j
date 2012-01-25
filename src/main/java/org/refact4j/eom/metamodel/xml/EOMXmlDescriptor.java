package org.refact4j.eom.metamodel.xml;

import org.refact4j.eom.metamodel.xml.reader.EntityDescriptorRepoXmlElementReader;
import org.refact4j.eom.metamodel.xml.writer.EntityDescriptorXmlNodeWriter;
import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.xml.DatasetConverterHolder;
import org.refact4j.xml.XmlDescriptor;
import org.refact4j.xml.XmlElement;
import org.refact4j.xml.XmlElementFactory;
import org.refact4j.xml.XmlElementHandler;
import org.refact4j.xml.impl.DefaultXmlElementHandler;
import org.refact4j.xml.reader.DefaultXmlElementReader;

import java.util.Arrays;
import java.util.Collection;

public class EOMXmlDescriptor implements XmlDescriptor {

    private final EntityDescriptorRepository repository;

    public EOMXmlDescriptor(EntityDescriptorRepository repository) {
        this.repository = repository;
    }

    public Collection<XmlElementFactory> getXmlElementFactories() {
        XmlElementFactory factory = new XmlElementFactory() {

            public XmlElement createXmlElement(DefaultXmlElementReader xmlElement) {
                return new EntityDescriptorRepoXmlElementReader(xmlElement);
            }

            public String getXmlElementTagName() {
                return "entityDescriptorRepository";
            }

        };
        return Arrays.asList(factory);
    }


    public XmlElementHandler[] getXmlElementHandlers(DatasetConverterHolder holder) {
        return new XmlElementHandler[]{new DefaultXmlElementHandler("entityDescriptorRepository", new EntityDescriptorXmlNodeWriter(
                this.repository, holder))};

    }

}
