package org.refact4j.eom.metamodel.xml;

import org.refact4j.eom.metamodel.xml.reader.EntityDescriptorRepoXmlElementReader;
import org.refact4j.eom.metamodel.xml.writer.EntityDescriptorXmlNodeWriter;
import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.xml.*;
import org.refact4j.xml.impl.DefaultXmlElementHandler;
import org.refact4j.xml.reader.DefaultXmlElementReader;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

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
        return Collections.singletonList(factory);
    }


    public XmlElementHandler[] getXmlElementHandlers(DatasetConverterHolder holder) {
        return new XmlElementHandler[]{new DefaultXmlElementHandler("entityDescriptorRepository", new EntityDescriptorXmlNodeWriter(
                this.repository, holder))};

    }

}
