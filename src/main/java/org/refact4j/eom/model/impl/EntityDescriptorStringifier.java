package org.refact4j.eom.model.impl;

import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.functor.Stringifier;

public class EntityDescriptorStringifier implements Stringifier<EntityDescriptor> {

    public static final EntityDescriptorStringifier DEFAULT = new EntityDescriptorStringifier() {

        @Override
        public String stringify(EntityDescriptor entityDescriptor) {
            return entityDescriptor.getName();
        }

    };

    public static final EntityDescriptorStringifier XML = new EntityDescriptorStringifier() {
        public String stringify(EntityDescriptor entityDescriptor) {
            return entityDescriptor.toEntity().toXmlString();
        }
    };

    @Override
    public String stringify(EntityDescriptor arg) {
        return DEFAULT.stringify(arg);
    }

}
