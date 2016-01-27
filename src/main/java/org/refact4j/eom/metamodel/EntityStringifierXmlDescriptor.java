package org.refact4j.eom.metamodel;

import org.refact4j.eom.metamodel.xml.reader.EntityStringifiersXmlElementReader;
import org.refact4j.eom.metamodel.xml.writer.EntityStringifierXmlNodeWrite;
import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.xml.*;
import org.refact4j.xml.impl.DefaultXmlElementHandler;
import org.refact4j.xml.reader.DefaultXmlElementReader;

import java.util.Arrays;
import java.util.Collection;

public class EntityStringifierXmlDescriptor implements XmlDescriptor {

    private static final String ROOT_TAGNAME = "stringifiers";

    private final EntityDescriptorRepository repository;

    public EntityStringifierXmlDescriptor(EntityDescriptorRepository repository) {
        this.repository = repository;
    }

    public Collection<XmlElementFactory> getXmlElementFactories() {
        XmlElementFactory factory = new XmlElementFactory() {

            public XmlElement createXmlElement(DefaultXmlElementReader xmlElement) {
                return new EntityStringifiersXmlElementReader(xmlElement, repository);
            }

            public String getXmlElementTagName() {
                return ROOT_TAGNAME;
            }

        };
        return Arrays.asList(factory);
    }


    public XmlElementHandler[] getXmlElementHandlers(DatasetConverterHolder holder) {
        return new XmlElementHandler[]{new DefaultXmlElementHandler(ROOT_TAGNAME, new EntityStringifierXmlNodeWrite(holder))};

    }

}
