package org.refact4j.eom.impl;

import org.refact4j.eom.*;
import org.refact4j.eom.metamodel.EntityStringifierAppenderDesc;
import org.refact4j.eom.metamodel.EntityStringifierDesc;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.eom.model.Field;
import org.refact4j.eom.model.Key;
import org.refact4j.eom.model.impl.EntityDescriptorImpl;
import org.refact4j.eom.xml.reader.EntityXmlReaderHelper;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractEntityStringifierRepoFactory implements EntityStringifierRepositoryFactory {

    private final String xmlStringifier;

    private final EntityDescriptorRepository entityDescriptorRepository;

    private final EntityDescriptorRepository metaModelRepository;

    private EntitySet entityObjectSet;

    private EntityStringifierRepoBuilder stringifierRepositoryBuilder;

    private EntityDescriptor objectEntityDescriptor;

    protected AbstractEntityStringifierRepoFactory(EntityDescriptorRepository entityDescriptorRepository,
                                                   String xmlStringifier, EntityDescriptorRepository metaModelRepository) {
        this.entityDescriptorRepository = entityDescriptorRepository;
        this.xmlStringifier = xmlStringifier;
        this.metaModelRepository = metaModelRepository;
    }

    public EntityStringifierRepo createEntityStringifierRepository() {
        return this.createEntityStringifierRepository(null);
    }

    private void init(EntityStringifierRepo initalEntityStringifierRepository) {
        entityObjectSet = new EntitySet(
                EntityXmlReaderHelper.unmarshal(metaModelRepository, xmlStringifier));
        stringifierRepositoryBuilder = EntityStringifierRepoBuilder.init(initalEntityStringifierRepository);
        stringifierRepositoryBuilder.setEntityDescriptorRepository(this.entityDescriptorRepository);
    }

    private EntityStringifierRepo createStringifiers() {
        Collection<EntityObject> stringifiers = entityObjectSet.stream()
                .filter(p -> p.getEntityDescriptor().equals(EntityStringifierDesc.INSTANCE))
                .collect(Collectors.toList());
        for (final EntityObject stringifier : stringifiers) {
            final Key keyEntityDescriptor = stringifier.get(EntityStringifierDesc.OBJECT_TYPE);
            EntityDescriptor entityDescriptor;
            if (keyEntityDescriptor == null && stringifiers.size() == 1) {
                entityDescriptor = entityDescriptorRepository.values().iterator().next();
            } else {
                entityDescriptor = entityDescriptorRepository.getEntityDescriptor(keyEntityDescriptor.getFieldValue(
                        objectEntityDescriptor.getField("name")).toString());
            }
            EntityStringifier entityObjectStringifierFunctor = createStringifier(stringifier, entityDescriptor);
            stringifierRepositoryBuilder.add(entityDescriptor, entityObjectStringifierFunctor);
            ((EntityDescriptorImpl) entityDescriptor).setEntityStringifier(entityObjectStringifierFunctor);

        }
        return stringifierRepositoryBuilder.getStringifierRepository();

    }

    private EntityStringifier createStringifier(final EntityObject stringifier, EntityDescriptor entityDescriptor) {
        final List<EntityObject> appenders = entityObjectSet.stream()
                .filter(arg -> arg.getEntityDescriptor().equals(EntityStringifierAppenderDesc.INSTANCE) && arg.get(EntityStringifierAppenderDesc.STRINGIFIER).equals(stringifier.getKey()))
                .collect(Collectors.toList());

        Collections.sort(appenders, (o1, o2) -> ((Integer) o1.get("id")).compareTo(((Integer) o2.get("id"))));

        EntityStringifier stringifierFunctor = new EntityStringifier();
        String stringifierName = stringifier.get(EntityStringifierDesc.NAME);
        if (stringifierName == null) {
            stringifierName = entityDescriptor.getName() + "Stringifier";
        }
        stringifierFunctor.setName(stringifierName);
        stringifierFunctor.setEntityDescriptor(entityDescriptor);
        int count = 1;
        for (EntityObject appender : appenders) {
            EntityObject stringifierAppenderEntity = EntityObjectBuilder.init(EntityStringifierAppenderDesc.INSTANCE)
                    .get();
            stringifierAppenderEntity.set(EntityStringifierAppenderDesc.STRING, appender
                    .get(EntityStringifierAppenderDesc.STRING));
            stringifierAppenderEntity.set(EntityStringifierAppenderDesc.ID, count++);
            Key keyField = appender.get(EntityStringifierAppenderDesc.FIELD);
            if (keyField != null) {
                String fieldName = (String) keyField.getFieldValue(keyField.getEntityDescriptor().getField("name"));
                Field field = entityDescriptor.getField(fieldName);
                stringifierAppenderEntity.set(EntityStringifierAppenderDesc.FIELD, field.toEntity());
            }
            stringifierFunctor.getAppenders().add(stringifierAppenderEntity);
        }
        return stringifierFunctor;
    }

    public EntityStringifierRepo createEntityStringifierRepository(
            EntityStringifierRepo initalEntityStringifierRepository) {
        init(initalEntityStringifierRepository);
        objectEntityDescriptor = metaModelRepository.getEntityDescriptor("entityDescriptor");
        return createStringifiers();
    }
}
