package org.refact4j.eom.metamodel.xml.reader;

import org.refact4j.eom.metamodel.EntityDescriptorDesc;
import org.refact4j.xml.DatasetConverterHolder;
import org.refact4j.xml.XmlAttributes;
import org.refact4j.xml.XmlElement;
import org.refact4j.xml.reader.DefaultXmlElementReader;

public class EntityDescriptorRepoXmlElementReader extends DefaultXmlElementReader {

    public EntityDescriptorRepoXmlElementReader(DatasetConverterHolder dataSetConverterHolder) {
        super(dataSetConverterHolder);
    }

    @Override
    public XmlElement createChildXmlElement(String localName, String qName, XmlAttributes attributes) {
        if (localName.equals(EntityDescriptorDesc.INSTANCE.getName())) {
            return new EntityDescriptorXmlElementReader(attributes, this);
        }
        return super.createChildXmlElement(localName, qName, attributes);
    }

}
