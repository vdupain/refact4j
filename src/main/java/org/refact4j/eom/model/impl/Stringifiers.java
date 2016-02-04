package org.refact4j.eom.model.impl;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.Field;
import org.refact4j.eom.xml.writer.EntityXmlWriterHelper;
import org.refact4j.function.Stringifier;
import org.refact4j.util.NotImplementedException;

public interface Stringifiers {

    Stringifier<Field> FIELD_FULLNAME = field ->field.getFullName();
    Stringifier<Field> FIELD_NAME = field ->field.getName();
    Stringifier<Field> FIELD_PRETTY = field ->field.getPrettyName() != null ? field.getPrettyName() : field.getName();
    Stringifier<Field> FIELD_XML = field -> {throw new NotImplementedException();};
    Stringifier<EntityDescriptor> ENTITY_DESCRIPTOR_XML = entityDescriptor -> entityDescriptor.toEntity().toXmlString();
    Stringifier<EntityDescriptor> ENTITY_DESCRIPTOR_NAME = entityDescriptor -> entityDescriptor.getName();
    Stringifier<EntityObject> ENTITY_XML = e -> EntityXmlWriterHelper.dump(e);

}
