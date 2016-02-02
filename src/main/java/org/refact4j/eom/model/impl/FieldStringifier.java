package org.refact4j.eom.model.impl;

import org.refact4j.eom.model.Field;
import org.refact4j.functor.Stringifier;
import org.refact4j.util.NotImplementedException;

public interface FieldStringifier extends Stringifier<Field> {

    FieldStringifier PRETTY = new FieldStringifier() {
        public String stringify(Field field) {
            return field.getPrettyName() != null ? field.getPrettyName() : field.getName();
        }

    };


    FieldStringifier XML = new FieldStringifier() {
        public String stringify(Field field) {
            throw new NotImplementedException();
        }
    };

    default String stringify(Field field) {
        return field.getName();
    }


}
