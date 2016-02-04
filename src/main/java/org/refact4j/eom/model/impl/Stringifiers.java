package org.refact4j.eom.model.impl;

import org.refact4j.eom.model.Field;
import org.refact4j.function.Stringifier;
import org.refact4j.util.NotImplementedException;

public interface Stringifiers {

    Stringifier<Field> FULLNAME = field ->field.getFullName();
    Stringifier<Field> NAME = field ->field.getName();
    Stringifier<Field> PRETTY = field ->field.getPrettyName() != null ? field.getPrettyName() : field.getName();
    Stringifier<Field> XML = field -> {throw new NotImplementedException();};

}
