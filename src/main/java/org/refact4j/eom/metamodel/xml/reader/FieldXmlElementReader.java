package org.refact4j.eom.metamodel.xml.reader;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntityObjectBuilder;
import org.refact4j.eom.metamodel.DataTypeType;
import org.refact4j.eom.metamodel.EntityDescriptorDesc;
import org.refact4j.eom.metamodel.FieldDesc;
import org.refact4j.eom.model.Key;
import org.refact4j.eom.model.KeyBuilder;
import org.refact4j.xml.DatasetConverterHolder;
import org.refact4j.xml.XmlAttributes;
import org.refact4j.xml.XmlHelper;
import org.refact4j.xml.reader.DefaultXmlElementReader;

class FieldXmlElementReader extends DefaultXmlElementReader {

    private final EntityObject field;

    public FieldXmlElementReader(XmlAttributes xmlAttrs, DatasetConverterHolder dataSetConverterHolder,
                                 EntityObject entityDescEntity) {
        super(dataSetConverterHolder);

        field = EntityObjectBuilder.init(FieldDesc.INSTANCE).get();
        field.set(FieldDesc.NAME, XmlHelper.getAttrValue(FieldDesc.NAME.getName(), xmlAttrs));
        field.set(FieldDesc.ENTITY_DESC, entityDescEntity);
        field.set(FieldDesc.PRETTY_NAME, XmlHelper.getAttrValue(FieldDesc.PRETTY_NAME.getName(), xmlAttrs, null));
        field.set(FieldDesc.IS_KEY, XmlHelper.getBooleanAttrValue(FieldDesc.IS_KEY.getName(), xmlAttrs, false));
        field.set(FieldDesc.NULLABLE, XmlHelper.getBooleanAttrValue(FieldDesc.NULLABLE.getName(), xmlAttrs, true));
        field.set(FieldDesc.VISIBLE, XmlHelper.getBooleanAttrValue(FieldDesc.VISIBLE.getName(), xmlAttrs, true));
        field.set(FieldDesc.EDITABLE, XmlHelper.getBooleanAttrValue(FieldDesc.EDITABLE.getName(), xmlAttrs, true));
        field.set(FieldDesc.DEFAULT_VALUE, XmlHelper.getAttrValue(FieldDesc.DEFAULT_VALUE.getName(), xmlAttrs, null));
        field.set(FieldDesc.MIN_VALUE, XmlHelper.getAttrValue(FieldDesc.MIN_VALUE.getName(), xmlAttrs, null));
        field.set(FieldDesc.MAX_VALUE, XmlHelper.getAttrValue(FieldDesc.MAX_VALUE.getName(), xmlAttrs, null));
        String attrValue = XmlHelper.getAttrValue(FieldDesc.ORDER.getName(), xmlAttrs, null);
        if (attrValue != null) {
            field.set(FieldDesc.ORDER, Integer.valueOf(attrValue));
        }
        attrValue = XmlHelper.getAttrValue(FieldDesc.MAX_LENGTH.getName(), xmlAttrs, null);
        if (attrValue != null) {
            field.set(FieldDesc.MAX_LENGTH, Integer.valueOf(attrValue));
        }
        attrValue = XmlHelper.getAttrValue(FieldDesc.MIN_LENGTH.getName(), xmlAttrs, null);
        if (attrValue != null) {
            field.set(FieldDesc.MIN_LENGTH, Integer.valueOf(attrValue));
        }
        String targetEntityDescriptorName = XmlHelper.getAttrValue(FieldDesc.TARGET.getName(), xmlAttrs, null);
        if (targetEntityDescriptorName != null) {
            Key targetEntityDescriptorKey = KeyBuilder.init(EntityDescriptorDesc.INSTANCE).set(
                    EntityDescriptorDesc.NAME, targetEntityDescriptorName).get();
            field.set(FieldDesc.TARGET, targetEntityDescriptorKey);
            String inverseRelationFieldName = XmlHelper.getAttrValue(FieldDesc.INVERSE_RELATION_FIELD.getName(),
                    xmlAttrs, null);
            if (inverseRelationFieldName != null) {
                Key keyField = KeyBuilder.init(FieldDesc.INSTANCE).set(FieldDesc.NAME, inverseRelationFieldName).set(
                        FieldDesc.ENTITY_DESC, targetEntityDescriptorKey).get();
                field.set(FieldDesc.INVERSE_RELATION_FIELD, keyField);
            }
        }
        String dataTypeName = XmlHelper.getAttrValue(FieldDesc.DATA_TYPE.getName(), xmlAttrs, null);
        if (dataTypeName != null) {
            field.set(FieldDesc.DATA_TYPE, KeyBuilder.init(DataTypeType.INSTANCE).set(DataTypeType.NAME, dataTypeName)
                    .get());
        }
        this.add(field);
    }

}
