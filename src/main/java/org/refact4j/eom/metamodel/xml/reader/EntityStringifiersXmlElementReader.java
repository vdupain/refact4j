package org.refact4j.eom.metamodel.xml.reader;

import org.refact4j.eom.metamodel.EntityStringifierDesc;
import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.xml.DatasetConverterHolder;
import org.refact4j.xml.XmlAttributes;
import org.refact4j.xml.XmlElement;
import org.refact4j.xml.reader.DefaultXmlElementReader;

public class EntityStringifiersXmlElementReader extends DefaultXmlElementReader {
    private final EntityDescriptorRepository repository;

    public EntityStringifiersXmlElementReader(DatasetConverterHolder datasetConverterHolder,
                                              EntityDescriptorRepository repository) {
        super(datasetConverterHolder);
        this.repository = repository;
    }

    @Override
    public XmlElement createChildXmlElement(String localName, String qName, XmlAttributes attributes) {
        if (localName.equals(EntityStringifierDesc.INSTANCE.getName())) {
            return new EntityStringifierXmlElementReader(attributes, this, repository);
        }
        return super.createChildXmlElement(localName, qName, attributes);
    }

}
