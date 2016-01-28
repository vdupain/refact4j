package org.refact4j.eom.annotations;

import org.refact4j.eom.metamodel.DefaultEntityDescriptorRepoFactory;
import org.refact4j.eom.model.DataTypeEnum;
import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.eom.model.impl.EntityDescriptorImpl;

import java.lang.reflect.Method;
import java.util.Date;

public class AnnotationEntityDescriptorRepoFactory extends DefaultEntityDescriptorRepoFactory {

    private final Class<?>[] beanClasses;

    private AnnotationEntityDescriptorRepoFactory(Class<?>[] beanClasses,
                                                  EntityDescriptorRepository metaModelEntityDescRepo, String xmlMetaModel,
                                                  EntityDescriptorRepository initialEntityDescriptorRepository) {
        super(metaModelEntityDescRepo, xmlMetaModel, initialEntityDescriptorRepository);
        this.beanClasses = beanClasses;
    }

    public static DefaultEntityDescriptorRepoFactory init(
            EntityDescriptorRepository metaModelEntityDescriptorRepository, Class<?>[] beanClasses,
            EntityDescriptorRepository initialEntityDescriptorRepository) {
        EntityAnnotationsHelper annotations = new EntityAnnotationsHelper();
        StringBuffer xmlMetaModel = new StringBuffer("<dataset>" + "<entityDescriptorRepository>");
        for (Class<?> clazz : beanClasses) {
            new AnnotationsValidator().validate(annotations, clazz);
            if (annotations.hasEntityBindableAnnotation(clazz)) {
                xmlMetaModel.append("<entityDescriptor name='").append(annotations.getEntityBindableAnnotation(clazz).entityDescriptor()).append("'>");
                for (Method method : clazz.getMethods()) {
                    if (annotations.hasEntityFieldAnnotation(method)) {
                        DataTypeEnum dataType = getDataType(method.getReturnType());
                        String target = null;
                        if (dataType.equals(DataTypeEnum.TO_ONE)) {
                            target = method.getReturnType().getSimpleName();
                        }

                        EntityField fieldAnnotation = annotations.getEntityFieldAnnotation(method);
                        xmlMetaModel.append("<field name='").append(fieldAnnotation.name()).append("' prettyName='").append(fieldAnnotation.prettyName()).append("' dataType='").append(dataType.toString()).append("' isKey='").append(fieldAnnotation.isKey()).append("' target='").append(target).append("'/>");
                    }
                }
                xmlMetaModel.append("</entityDescriptor>");
            }
        }
        xmlMetaModel.append("</entityDescriptorRepository>" + "</dataset>");
        return new AnnotationEntityDescriptorRepoFactory(beanClasses, metaModelEntityDescriptorRepository, xmlMetaModel
                .toString(), initialEntityDescriptorRepository);
    }

    private static DataTypeEnum getDataType(Class<?> clazz) {
        EntityAnnotationsHelper annotations = new EntityAnnotationsHelper();
        if (clazz.equals(Integer.class) || clazz.equals(Integer.TYPE)) {
            return DataTypeEnum.INTEGER;
        } else if (clazz.equals(Double.class) || clazz.equals(Double.TYPE)) {
            return DataTypeEnum.DOUBLE;
        } else if (clazz.equals(Boolean.class) || clazz.equals(Boolean.TYPE)) {
            return DataTypeEnum.BOOLEAN;
        } else if (clazz.equals(Date.class)) {
            return DataTypeEnum.DATE;
        } else if (clazz.equals(String.class)) {
            return DataTypeEnum.STRING;
        } else if (annotations.hasEntityBindableAnnotation(clazz)) {
            return DataTypeEnum.TO_ONE;
        }
        return null;
    }

    @Override
    public EntityDescriptorRepository createEntityDescriptorRepository() {
        EntityDescriptorRepository repo = super.createEntityDescriptorRepository();
        for (Class<?> clazz : beanClasses) {
            ((EntityDescriptorImpl) repo.getEntityDescriptor(clazz.getSimpleName())).setBeanClass(clazz);
        }
        return repo;
    }
}
