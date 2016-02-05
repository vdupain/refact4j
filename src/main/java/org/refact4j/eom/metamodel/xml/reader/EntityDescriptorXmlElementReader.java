package org.refact4j.eom.metamodel.xml.reader;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntityObjectBuilder;
import org.refact4j.eom.metamodel.EntityDescriptorDesc;
import org.refact4j.eom.metamodel.FieldDesc;
import org.refact4j.eom.metamodel.PropertyDesc;
import org.refact4j.xml.DatasetConverterHolder;
import org.refact4j.xml.XmlAttributes;
import org.refact4j.xml.XmlElement;
import org.refact4j.xml.XmlHelper;
import org.refact4j.xml.reader.DefaultXmlElementReader;

public class EntityDescriptorXmlElementReader extends DefaultXmlElementReader {

    private final EntityObject entityDescr;

    public EntityDescriptorXmlElementReader(XmlAttributes xmlAttrs, DatasetConverterHolder dataSetConverterHolder) {
        super(dataSetConverterHolder);
        entityDescr = EntityObjectBuilder.init(EntityDescriptorDesc.INSTANCE).get();
        entityDescr
                .set(EntityDescriptorDesc.NAME, XmlHelper.getAttrValue(EntityDescriptorDesc.NAME.getName(), xmlAttrs));
        this.add(entityDescr);
    }

    @Override
    public XmlElement createChildXmlElement(String localName, String qName, XmlAttributes attributes) {
        if (localName.equals(FieldDesc.INSTANCE.getName())) {
            return new FieldXmlElementReader(attributes, this, entityDescr);
        } else if (localName.equals(PropertyDesc.INSTANCE.getName())) {
            return new PropertyXmlElementReader(attributes, this, this.entityDescr, null);
        }
        return super.createChildXmlElement(localName, qName, attributes);
    }

}
