package org.refact4j.eom.model.impl;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.Field;
import org.refact4j.eom.xml.writer.EntityXmlWriterHelper;
import org.refact4j.function.Stringifier;

public interface Stringifiers {

    Stringifier<Field> FIELD_FULLNAME = Field::getFullName;
    Stringifier<Field> FIELD_NAME = Field::getName;
    Stringifier<Field> FIELD_PRETTY = field -> field.getPrettyName() != null ? field.getPrettyName() : field.getName();
    Stringifier<Field> FIELD_XML = field -> {
        throw new RuntimeException("Not Implemented");
    };
    Stringifier<EntityDescriptor> ENTITY_DESCRIPTOR_XML = entityDescriptor -> entityDescriptor.toEntity().toXmlString();
    Stringifier<EntityDescriptor> ENTITY_DESCRIPTOR_NAME = EntityDescriptor::getName;
    Stringifier<EntityObject> ENTITY_XML = EntityXmlWriterHelper::dump;

}
