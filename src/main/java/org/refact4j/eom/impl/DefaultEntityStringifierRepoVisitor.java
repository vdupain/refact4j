package org.refact4j.eom.impl;

import org.refact4j.collection.DataSet;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntityStringifier;
import org.refact4j.eom.EntityStringifierRepo;
import org.refact4j.eom.EntityStringifierRepo.EntityStringifierRepoVisitor;
import org.refact4j.eom.metamodel.EntityStringifierAppenderDesc;
import org.refact4j.eom.metamodel.EntityStringifierDesc;
import org.refact4j.eom.metamodel.EntityStringifierXmlDescriptor;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.functor.UnaryPredicate;
import org.refact4j.visitor.Visitable;
import org.refact4j.xml.ToXmlString;
import org.refact4j.xml.impl.Dataset2XmlConverterImpl;

import java.util.List;

public class DefaultEntityStringifierRepoVisitor implements EntityStringifierRepoVisitor, ToXmlString {
    private final EntityDataSetImpl dataSet = new EntityDataSetImpl();

    private final EntityDescriptorRepository entityDescriptorRepository;

    public DefaultEntityStringifierRepoVisitor(EntityDescriptorRepository entityDescriptorRepository) {
        this.entityDescriptorRepository = entityDescriptorRepository;
    }

    public String toXmlString() {
        Dataset2XmlConverterImpl converter = new Dataset2XmlConverterImpl();
        converter.register(new EntityStringifierXmlDescriptor(this.entityDescriptorRepository));
        List<EntityObject> entityObjects = dataSet.getAll(EntityStringifierDesc.INSTANCE,
                new UnaryPredicate<EntityObject>() {

                    public Boolean eval(EntityObject arg) {
                        return arg.get(EntityStringifierDesc.OBJECT_TYPE) == null
                                && arg.get(EntityStringifierDesc.NAME) == null;
                    }
                });
        dataSet.removeAll(entityObjects);
        return converter.marshal(dataSet);
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public void visitEntityStringifierRepository(EntityStringifierRepo entityObjectStringifierRepository) {
        for (final EntityDescriptor entityDescriptor : entityObjectStringifierRepository.keySet()) {
            EntityStringifier entityObjectStringifierFunctor = entityObjectStringifierRepository.get(entityDescriptor);
            EntityObject stringifierEntity = entityObjectStringifierFunctor.toEntity();
            dataSet.add(stringifierEntity);
            List<EntityObject> appenders = entityObjectStringifierFunctor.getAppenders();
            for (EntityObject appenderEntity : appenders) {
                appenderEntity.set(EntityStringifierAppenderDesc.STRINGIFIER, stringifierEntity);
            }
            dataSet.addAll(appenders);
        }
    }

    public void visit(Visitable visitable) {
    }

}
