package org.refact4j.eom.impl;

import org.refact4j.collection.DataSet;
import org.refact4j.eom.EntityList;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntityObjectBuilder;
import org.refact4j.eom.metamodel.DataTypeConverter;
import org.refact4j.eom.metamodel.PropertyDesc;
import org.refact4j.eom.metamodel.xml.EOMXmlDescriptor;
import org.refact4j.eom.model.DataType;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.Field;
import org.refact4j.eom.model.impl.PropertyImpl;
import org.refact4j.xml.ToXmlString;
import org.refact4j.xml.impl.Dataset2XmlConverterImpl;

public class DefaultMetaModelVisitor extends AbstractMetaModelVisitor implements ToXmlString {
    private final DataSet dataSet = new EntityDataSetImpl();

    public String toXmlString() {
        Dataset2XmlConverterImpl converter = new Dataset2XmlConverterImpl();
        converter.register(new EOMXmlDescriptor(entityDescriptorRepository));
        return converter.marshal(dataSet);
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public void visitEntityDescriptor(EntityDescriptor entityDescriptor) {
        dataSet.add(entityDescriptor.toEntity());
        EntityList properties = getPropertiesAsEntityList((PropertyImpl) entityDescriptor.getProperty(),
                entityDescriptor.toEntity(), null);
        dataSet.addAll(properties);
    }

    public void visitField(Field field) {
        dataSet.add(field.toEntity());
        EntityList properties = getPropertiesAsEntityList((PropertyImpl) field.getProperty(), null, field.toEntity());
        dataSet.addAll(properties);
    }

    private EntityList getPropertiesAsEntityList(PropertyImpl property, EntityObject entityDescEntity,
                                                 EntityObject fieldEntity) {
        EntityList list = new EntityListImpl();
        for (Object key : property.keySet()) {
            Object value = property.getProperty(key);
            if (value == null) {
                continue;
            }
            PropertyDesc.TypeVisitor typeVisitor = new PropertyDesc.TypeVisitor();
            typeVisitor.visit(value);
            EntityObject propertyEntity = EntityObjectBuilder.init(PropertyDesc.INSTANCE).getEntity();
            propertyEntity.set(PropertyDesc.KEY, key.toString());
            propertyEntity.set(PropertyDesc.DATA_TYPE, typeVisitor.dataTypeEntity);

            DataType dataType = typeVisitor.dataTypeEntity.getDataType();
            DataTypeConverter converter = new DataTypeConverter();
            converter.getValue2StringConverter().setValue(value);
            dataType.accept(converter);
            propertyEntity.set(PropertyDesc.VALUE, converter.getValue2StringConverter().getStringValue());

            if (entityDescEntity != null) {
                propertyEntity.set(PropertyDesc.ENTITY_DESC_DESC, entityDescEntity);
            }
            if (fieldEntity != null) {
                propertyEntity.set(PropertyDesc.FIELD_TYPE, fieldEntity);
            }
            list.add(propertyEntity);
        }
        return list;
    }

}