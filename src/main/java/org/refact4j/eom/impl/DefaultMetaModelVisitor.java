package org.refact4j.eom.impl;

import org.refact4j.eom.metamodel.xml.EOMXmlDescriptor;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.eom.model.Field;
import org.refact4j.eom.model.MetaModelVisitor;
import org.refact4j.xml.ToXmlString;
import org.refact4j.xml.impl.Dataset2XmlConverterImpl;

import java.util.Set;

public class DefaultMetaModelVisitor implements MetaModelVisitor, ToXmlString {
    private final Set dataSet = new EntityDataSet();
    private EntityDescriptorRepository entityDescriptorRepository;

    public void visitEntityDescriptorRepository(EntityDescriptorRepository entityDescriptorRepository) {
        this.entityDescriptorRepository = entityDescriptorRepository;
        for (EntityDescriptor entityDescriptor : entityDescriptorRepository.values()) {
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
    }

    public void visitField(Field field) {
        dataSet.add(field.toEntity());
    }

}