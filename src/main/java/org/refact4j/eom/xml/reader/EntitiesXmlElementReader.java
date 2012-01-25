package org.refact4j.eom.xml.reader;

import org.refact4j.eom.EntityFinder;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.xml.DatasetConverterHolder;
import org.refact4j.xml.XmlAttributes;
import org.refact4j.xml.XmlElement;
import org.refact4j.xml.reader.DefaultXmlElementReader;

public class EntitiesXmlElementReader extends DefaultXmlElementReader {
    private final EntityDescriptorRepository entityDescriptorRepository;

    private final EntityFinder finder;

    public EntitiesXmlElementReader(EntityDescriptorRepository entityDescriptorRepository,
                                    EntityFinder entityObjectFinder, DatasetConverterHolder holder) {
        super(holder);
        this.entityDescriptorRepository = entityDescriptorRepository;
        this.finder = entityObjectFinder;
    }

    public XmlElement createChildXmlElement(String localName, String qName, XmlAttributes attributes) {
        for (EntityDescriptor entityDescriptor : entityDescriptorRepository) {
            if (localName.equals(entityDescriptor.getName())) {
                return new EntityXmlElementReader(entityDescriptor, entityDescriptorRepository, finder, attributes, this,
                        null);
            }
        }
        return super.createChildXmlElement(localName, qName, attributes);
    }
}
