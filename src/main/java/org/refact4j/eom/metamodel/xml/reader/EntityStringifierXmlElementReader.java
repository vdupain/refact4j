package org.refact4j.eom.metamodel.xml.reader;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntityObjectBuilder;
import org.refact4j.eom.metamodel.EntityDescriptorDesc;
import org.refact4j.eom.metamodel.EntityStringifierAppenderDesc;
import org.refact4j.eom.metamodel.EntityStringifierDesc;
import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.eom.model.Key;
import org.refact4j.eom.model.KeyBuilder;
import org.refact4j.xml.DataSetConverterHolder;
import org.refact4j.xml.XmlAttributes;
import org.refact4j.xml.XmlElement;
import org.refact4j.xml.XmlHelper;
import org.refact4j.xml.reader.DefaultXmlElementReader;

public class EntityStringifierXmlElementReader extends DefaultXmlElementReader {

    private final EntityDescriptorRepository repository;
    private final EntityObject stringifier;

    public EntityStringifierXmlElementReader(XmlAttributes xmlAttrs, DataSetConverterHolder dataSetConverterHolder,
                                             EntityDescriptorRepository repository) {
        super(dataSetConverterHolder);
        this.repository = repository;
        stringifier = EntityObjectBuilder.init(EntityStringifierDesc.INSTANCE).get();
        String entityDescName = XmlHelper.getAttrValue(EntityStringifierDesc.OBJECT_TYPE.getName(), xmlAttrs);
        Key entityDescKey = KeyBuilder.init(EntityDescriptorDesc.INSTANCE).set(EntityDescriptorDesc.NAME,
                entityDescName).getKey();
        stringifier.set(EntityStringifierDesc.OBJECT_TYPE, entityDescKey);
        stringifier.set(EntityStringifierDesc.NAME, XmlHelper.getAttrValue(EntityStringifierDesc.NAME.getName(),
                xmlAttrs));
        this.add(stringifier);
    }

    @Override
    public XmlElement createChildXmlElement(String localName, String qName, XmlAttributes attributes) {
        if (localName.equals(EntityStringifierAppenderDesc.INSTANCE.getName())) {
            return new EntityStringifierAppenderXmlElementReader(attributes, this, stringifier, repository);
        }
        return super.createChildXmlElement(localName, qName, attributes);
    }

}
