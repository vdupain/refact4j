package org.refact4j.eom.model.impl;

import org.refact4j.eom.model.Field;
import org.refact4j.functor.Stringifier;
import org.refact4j.util.NotImplementedException;

public abstract class AbstractFieldStringifierFunctor implements Stringifier<Field> {

    public static final AbstractFieldStringifierFunctor PRETTY = new AbstractFieldStringifierFunctor() {

        @Override
        public String stringify(Field field) {
            return field.getPrettyName() != null ? field.getPrettyName() : field.getName();
        }

    };

    public static final AbstractFieldStringifierFunctor DEFAULT = new AbstractFieldStringifierFunctor() {

        @Override
        public String stringify(Field field) {
            return field.getName();
        }

    };

    public static final AbstractFieldStringifierFunctor XML = new AbstractFieldStringifierFunctor() {
        public String stringify(Field field) {
            throw new NotImplementedException();
        }
    };

}
