package org.refact4j.eom.model;

import org.refact4j.eom.model.EntityDescriptor.EntityDescriptorVisitor;
import org.refact4j.eom.model.EntityDescriptorRepository.EntityDescriptorRepositoryVisitor;

public interface MetaModelVisitor extends EntityDescriptorRepositoryVisitor, EntityDescriptorVisitor, FieldVisitor {

}
