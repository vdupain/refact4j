package org.refact4j.eom.metamodel;

import org.refact4j.eom.ConverterHelper;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntityUtils;
import org.refact4j.eom.impl.EntityDataSet;
import org.refact4j.eom.impl.EntitySet;
import org.refact4j.eom.metamodel.xml.EOMXmlDescriptor;
import org.refact4j.eom.model.*;
import org.refact4j.eom.model.impl.AbstractRelationField;
import org.refact4j.eom.xml.EntityXmlDescriptor;
import org.refact4j.xml.impl.Dataset2XmlConverterImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultEntityDescriptorRepoFactory implements EntityDescriptorRepositoryFactory {
    private EntityDescriptorRepository initialEntityDescriptorRepository;

    private EntityDescriptorRepository metaModelEntityDescriptorRepository;

    private String xmlMetaModel;

    private EntitySet metaModelSet;

    private DefaultEntityDescriptorRepoFactory() {

    }

    protected DefaultEntityDescriptorRepoFactory(EntityDescriptorRepository metaModelEntityDescriptorRepository,
                                                 String xmlMetaModel, EntityDescriptorRepository initialEntityDescriptorRepository) {
        this();
        setInitialEntityDescriptorRepository(initialEntityDescriptorRepository);
        setMetaModelEntityDescriptorRepository(metaModelEntityDescriptorRepository);
        setXmlMetaModel(xmlMetaModel);
    }

    private static DefaultEntityDescriptorRepoFactory createInstance() {
        return new DefaultEntityDescriptorRepoFactory();
    }

    public static DefaultEntityDescriptorRepoFactory init(
            EntityDescriptorRepository metaModelEntityDescriptorRepository, String xmlMetaModel,
            EntityDescriptorRepository initialEntityDescriptorRepository) {
        DefaultEntityDescriptorRepoFactory factory = createInstance();
        factory.setInitialEntityDescriptorRepository(initialEntityDescriptorRepository);
        factory.setMetaModelEntityDescriptorRepository(metaModelEntityDescriptorRepository);
        factory.setXmlMetaModel(xmlMetaModel);
        return factory;
    }

    public EntityDescriptorRepository createEntityDescriptorRepository() {
        Set actualDataset = new EntityDataSet();
        Dataset2XmlConverterImpl converter = new Dataset2XmlConverterImpl();
        converter.register(new EntityXmlDescriptor(EOMMetaModelRepository.get()));
        converter.register(new EOMXmlDescriptor(metaModelEntityDescriptorRepository));
        converter.unmarshal(xmlMetaModel, actualDataset);
        metaModelSet = new EntitySet(actualDataset);
        metaModelSet.addAll(DataTypeType.DATA_TYPES);
        EntityDescriptorRepositoryBuilder repoBuilder = EntityDescriptorRepositoryBuilder
                .init(initialEntityDescriptorRepository);
        List<EntityObject> entityDescs = metaModelSet.stream()
                .filter(t -> t.getEntityDescriptor().equals(EntityDescriptorDesc.INSTANCE))
                .collect(Collectors.toList());
        Map<EntityDescriptor, EntityDescriptorBuilder> entityDescBuilderMap = new HashMap<>();
        for (EntityObject entityDescEntity : entityDescs) {
            final String entityDescName = entityDescEntity.get(EntityDescriptorDesc.NAME);
            EntityDescriptorBuilder entityDescriptorBuilder = EntityDescriptorBuilder.init(entityDescName);
            List<EntityObject> fields = metaModelSet.stream()
                    .filter(FieldDesc.getFieldsForEntityDescriptor(entityDescName))
                    .collect(Collectors.toList());
            createFields(entityDescriptorBuilder, fields);
            EntityDescriptor entityDescriptor = entityDescriptorBuilder.get();
            repoBuilder.add(entityDescriptor);
            entityDescBuilderMap.put(entityDescriptor, entityDescriptorBuilder);
        }
        EntityDescriptorRepository repository = repoBuilder.getEntityDescriptorsRepository();

        for (final EntityDescriptor entityDescriptor : repository.values()) {
            EntityDescriptorBuilder builder = entityDescBuilderMap.get(entityDescriptor);
            List<EntityObject> relations = metaModelSet.stream()
                    .filter(FieldDesc.getRelationFieldsForEntityDescriptor(entityDescriptor.getName()))
                    .collect(Collectors.toList());
            createRelationFields(repository, builder, relations);
        }
        for (final EntityDescriptor entityDescriptor : repository.values()) {
            List<EntityObject> relations = metaModelSet.stream()
                    .filter(FieldDesc.getRelationFieldsForEntityDescriptor(entityDescriptor.getName()))
                    .collect(Collectors.toList());
            createInverseRelationships(repository, relations);
        }
        return repository;
    }

    private void createInverseRelationships(EntityDescriptorRepository repository, List<EntityObject> relations) {
        for (final EntityObject relationEntity : relations) {
            String parentEntityDescriptorName = (String) relationEntity.get(FieldDesc.ENTITY_DESC).getFieldValue(
                    EntityDescriptorDesc.NAME);
            EntityDescriptor parentEntityDescriptor = repository.getEntityDescriptor(parentEntityDescriptorName);
            Key inverseRelationFieldKey = relationEntity.get(FieldDesc.INVERSE_RELATION_FIELD);
            String inverseRelationFieldname = null;
            if (inverseRelationFieldKey != null) {
                inverseRelationFieldname = (String) inverseRelationFieldKey.getFieldValue(FieldDesc.NAME);
            }
            final AbstractRelationField field = (AbstractRelationField) parentEntityDescriptor
                    .getField(relationEntity.get(FieldDesc.NAME));
            RelationField inverseRelationField = (RelationField) field.getTargetEntityDescriptor().getField(
                    inverseRelationFieldname);
            if (inverseRelationFieldname != null && inverseRelationField == null) {
                throw new RuntimeException("The inverse relationship '" + inverseRelationFieldname
                        + "' does not exist in the EntityDescriptor '" + field.getTargetEntityDescriptor() + "'");
            }

            FieldVisitor visitor = new DefaultFieldVisitor() {
                public void defaultVisit(RelationField relationField) {
                    field.setInverseRelationField(relationField);
                }

                public void visitManyToOneRelationField(ManyToOneRelationField manyToOneRelationField) {
                    if (relationEntity.get(FieldDesc.DATA_TYPE).equals(DataTypeType.MANY_TO_ONE_RELATION_DATA_TYPE.getKey())) {
                        throw new RuntimeException(
                                "A ManyToOne relationship cannot be the inverse relationship of another ManyToOne");
                    }
                    defaultVisit(manyToOneRelationField);
                }

                public void visitOneToManyRelationField(OneToManyRelationField oneToManyRelationField) {
                    if (relationEntity.get(FieldDesc.DATA_TYPE)
                            .equals(DataTypeType.ONE_TO_MANY_RELATION_DATA_TYPE.getKey())) {
                        throw new RuntimeException(
                                "A OneToMany relationship cannot be the inverse relationship of another OneToMany");
                    }
                    defaultVisit(oneToManyRelationField);
                }

            };
            if (inverseRelationField != null) {
                inverseRelationField.accept(visitor);
            }
        }
    }

    private void createRelationFields(final EntityDescriptorRepository repository, EntityDescriptorBuilder builder,
                                      List<EntityObject> fields) {
        FieldBuilder fieldBuilder = (fieldEntity, fieldFactory) -> {
            Key targetKey = fieldEntity.get(FieldDesc.TARGET);
            EntityDescriptor targetEntityDescriptor = repository.getEntityDescriptor((String) targetKey
                    .getFieldValue(EntityDescriptorDesc.NAME));
            Field field = null;

            if (fieldEntity.get(FieldDesc.DATA_TYPE).equals(DataTypeType.MANY_TO_ONE_RELATION_DATA_TYPE.getKey())) {
                field = fieldFactory.createManyToOneRelationField(targetEntityDescriptor);
            } else if (fieldEntity.get(FieldDesc.DATA_TYPE)
                    .equals(DataTypeType.ONE_TO_MANY_RELATION_DATA_TYPE.getKey())) {
                field = fieldFactory.createOneToManyRelationField(targetEntityDescriptor);
            }
            return field;
        };
        this.createFields(builder, fields, fieldBuilder);
    }

    private void createFields(EntityDescriptorBuilder builder, List<EntityObject> fields) {
        FieldBuilder fieldBuilder = (fieldEntity, fieldFactory) -> {
            Key keyDataType = fieldEntity.get(FieldDesc.DATA_TYPE);
            DataTypeEntity dataTypeEntity = (DataTypeEntity) metaModelSet.stream()
                    .filter(p -> p.getKey().equals(keyDataType))
                    .findFirst().get();
            DataType dataType = dataTypeEntity.getDataType();
            dataType.accept(fieldFactory);
            return fieldFactory.get();
        };
        this.createFields(builder, fields, fieldBuilder);
    }

    private void createFields(EntityDescriptorBuilder builder, List<EntityObject> fields, FieldBuilder fieldBuilder) {
        for (EntityObject fieldEntity : fields) {
            EntityUtils.applyEmptyDefaultValues(fieldEntity);
            FieldFactory fieldFactory = FieldFactory.init(builder, fieldEntity.get(FieldDesc.NAME));
            Field dummyField = fieldBuilder.getField(fieldEntity, fieldFactory);
            fieldFactory.setPrettyName(fieldEntity.get(FieldDesc.PRETTY_NAME)).setNullable(
                    fieldEntity.get(FieldDesc.NULLABLE)).setVisible(fieldEntity.get(FieldDesc.VISIBLE)).setEditable(
                    fieldEntity.get(FieldDesc.EDITABLE)).setOrder(fieldEntity.get(FieldDesc.ORDER)).setDefaultValue(
                    ConverterHelper.convertString2Value(fieldEntity.get(FieldDesc.DEFAULT_VALUE), dummyField))
                    .setMinValue(
                            (Number) ConverterHelper.convertString2Value(fieldEntity.get(FieldDesc.MIN_VALUE),
                                    dummyField)).setMaxValue(
                    (Number) ConverterHelper.convertString2Value(fieldEntity.get(FieldDesc.MAX_VALUE),
                            dummyField)).setMinLength(fieldEntity.get(FieldDesc.MIN_LENGTH)).setMaxLength(
                    fieldEntity.get(FieldDesc.MAX_LENGTH));
            Field field = fieldBuilder.getField(fieldEntity, fieldFactory);
            builder.addField(field);
            if (Boolean.TRUE.equals(fieldEntity.get(FieldDesc.IS_KEY))) {
                builder.addKeyField(field);
            }
        }
    }

    private void setInitialEntityDescriptorRepository(EntityDescriptorRepository initialEntityDescriptorRepository) {
        this.initialEntityDescriptorRepository = initialEntityDescriptorRepository;
    }

    private void setMetaModelEntityDescriptorRepository(EntityDescriptorRepository metaModelEntityDescriptorRepository) {
        this.metaModelEntityDescriptorRepository = metaModelEntityDescriptorRepository;
    }

    private void setXmlMetaModel(String xmlMetaModel) {
        this.xmlMetaModel = xmlMetaModel;
    }

    interface FieldBuilder {
        Field getField(EntityObject fieldEntity, FieldFactory fieldFactory);
    }

}
