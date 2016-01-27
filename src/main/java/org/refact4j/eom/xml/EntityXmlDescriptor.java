package org.refact4j.eom.xml;

import org.refact4j.eom.EntityFinder;
import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.eom.model.EntityDescriptorRepositoryHolder;
import org.refact4j.eom.xml.reader.EntitiesXmlElementReader;
import org.refact4j.eom.xml.writer.EntityXmlNodeWriter;
import org.refact4j.xml.*;
import org.refact4j.xml.impl.DefaultXmlElementHandler;
import org.refact4j.xml.reader.DefaultXmlElementReader;

import java.util.Arrays;
import java.util.Collection;

public class EntityXmlDescriptor implements XmlDescriptor {

    private class EntityXmlElementFactory implements XmlElementFactory, EntityDescriptorRepositoryHolder {
        public XmlElement createXmlElement(DefaultXmlElementReader xmlElement) {
            return new EntitiesXmlElementReader(entityDescriptorRepository, (EntityFinder) xmlElement.getFinder(),
                    xmlElement);
        }

        public String getXmlElementTagName() {
            return ROOT_TAGNAME;
        }

        public EntityDescriptorRepository getEntityDescriptorRepository() {
            return entityDescriptorRepository;
        }

    }

    public static final String ROOT_TAGNAME = "entities";

    private final EntityDescriptorRepository entityDescriptorRepository;

    public EntityXmlDescriptor(EntityDescriptorRepository entityDescriptorRepository) {
        this.entityDescriptorRepository = entityDescriptorRepository;
    }

    public Collection<XmlElementFactory> getXmlElementFactories() {
        return Arrays.asList(new XmlElementFactory[]{new EntityXmlElementFactory()});
    }

    public XmlElementHandler[] getXmlElementHandlers(DatasetConverterHolder holder) {
        return new XmlElementHandler[]{new DefaultXmlElementHandler(ROOT_TAGNAME, new EntityXmlNodeWriter(
                entityDescriptorRepository, holder))};

    }

}
