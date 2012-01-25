package org.refact4j.eom;

import org.refact4j.model.FooDesc;
import org.refact4j.util.SerializableTestCase;

import java.io.Serializable;

public class FieldSerializableTestCase extends SerializableTestCase {

    @Override
    protected Serializable createSerializableInstance() {
        return FooDesc.ID;
    }
}
