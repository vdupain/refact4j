package org.refact4j.eom.impl;

import org.refact4j.collection.Set;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntityObjectBuilder;
import org.refact4j.eom.metamodel.DataTypeConverter;
import org.refact4j.eom.metamodel.PropertyDesc;
import org.refact4j.eom.metamodel.xml.EOMXmlDescriptor;
import org.refact4j.eom.model.*;
import org.refact4j.eom.model.impl.PropertyImpl;
import org.refact4j.xml.ToXmlString;
import org.refact4j.xml.impl.Dataset2XmlConverterImpl;

public class DefaultMetaModelVisitor implements MetaModelVisitor, ToXmlString {
    private final Set dataSet = new EntityDataSet();
    private EntityDescriptorRepository entityDescriptorRepository;

    public void visitEntityDescriptorRepository(EntityDescriptorRepository entityDescriptorRepository) {
        this.entityDescriptorRepository = entityDescriptorRepository;
        for (EntityDescriptor entityDescriptor : entityDescriptorRepository) {
            entityDescriptor.accept(this);
            for (Field field : entityDescriptor.getFields()) {
                field.accept(this);
            }
        }
    }

    public String toXmlString() {
        Dataset2XmlConverterImpl converter = new Dataset2XmlConverterImpl();
        converter.register(new EOMXmlDescriptor(entityDescriptorRepository));
        return converter.marshal(dataSet);
    }

    public Set getDataSet() {
        return dataSet;
    }

    public void visitEntityDescriptor(EntityDescriptor entityDescriptor) {
        dataSet.add(entityDescriptor.toEntity());
        org.refact4j.eom.EntityList properties = getPropertiesAsEntityList((PropertyImpl) entityDescriptor.getProperty(),
                entityDescriptor.toEntity(), null);
        dataSet.addAll(properties);
    }

    public void visitField(Field field) {
        dataSet.add(field.toEntity());
        org.refact4j.eom.EntityList properties = getPropertiesAsEntityList((PropertyImpl) field.getProperty(), null, field.toEntity());
        dataSet.addAll(properties);
    }

    private org.refact4j.eom.EntityList getPropertiesAsEntityList(PropertyImpl property, EntityObject entityDescEntity,
                                                                  EntityObject fieldEntity) {
        org.refact4j.eom.EntityList list = new EntityList();
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