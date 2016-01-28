package org.refact4j.eom;

import org.refact4j.model.BarDesc;
import org.refact4j.model.FooDesc;
import org.refact4j.util.SerializableTestCase;

import java.io.Serializable;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class EntitySerializableTestCase extends SerializableTestCase {

    @Override
    protected Serializable createSerializableInstance() {
        EntityObject bar = EntityObjectBuilder.init(BarDesc.INSTANCE).set(BarDesc.ID, 1)
                .getEntity();
        return EntityObjectBuilder.init(FooDesc.INSTANCE).set(FooDesc.ID, 1).set(
                FooDesc.NAME, "foo").set(FooDesc.FLAG, true).set(FooDesc.BEGIN_DATE, new Date()).set(
                FooDesc.BAR, bar).getEntity();
    }

    protected void checkObject(Serializable expected, Serializable actual) {
        assertEquals(expected, actual);
    }

}
