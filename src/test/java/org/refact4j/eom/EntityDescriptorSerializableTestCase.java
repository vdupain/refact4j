package org.refact4j.eom;

import org.refact4j.model.FooDesc;
import org.refact4j.util.SerializableTestCase;

import java.io.Serializable;


public class EntityDescriptorSerializableTestCase extends SerializableTestCase {

    @Override
    protected Serializable createSerializableInstance() {
        return FooDesc.INSTANCE;
    }
}
