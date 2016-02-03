package org.refact4j.eom.metamodel.xml.reader;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntityObjectBuilder;
import org.refact4j.eom.metamodel.DataTypeType;
import org.refact4j.eom.metamodel.PropertyDesc;
import org.refact4j.eom.model.KeyBuilder;
import org.refact4j.xml.DataSetConverterHolder;
import org.refact4j.xml.XmlAttributes;
import org.refact4j.xml.XmlHelper;
import org.refact4j.xml.reader.DefaultXmlElementReader;

class PropertyXmlElementReader extends DefaultXmlElementReader {

    public PropertyXmlElementReader(XmlAttributes xmlAttrs, DataSetConverterHolder dataSetConverterHolder,
                                    EntityObject entityDescTypeEntity, EntityObject fieldEntity) {
        super(dataSetConverterHolder);

        EntityObject property = EntityObjectBuilder.init(PropertyDesc.INSTANCE).getEntity();

        if (entityDescTypeEntity != null) {
            property.set(PropertyDesc.ENTITY_DESC_DESC, entityDescTypeEntity);
        }
        if (fieldEntity != null) {
            property.set(PropertyDesc.FIELD_TYPE, fieldEntity);
        }
        String dataTypeName = XmlHelper.getAttrValue(PropertyDesc.DATA_TYPE.getName(), xmlAttrs, null);
        if (dataTypeName != null) {
            property.set(PropertyDesc.DATA_TYPE, KeyBuilder.init(DataTypeType.INSTANCE).set(DataTypeType.NAME,
                    dataTypeName).getKey());
        }
        property.set(PropertyDesc.KEY, XmlHelper.getAttrValue(PropertyDesc.KEY.getName(), xmlAttrs, null));
        property.set(PropertyDesc.VALUE, XmlHelper.getAttrValue(PropertyDesc.VALUE.getName(), xmlAttrs, null));
        this.add(property);
    }

}
