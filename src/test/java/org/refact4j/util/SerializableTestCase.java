package org.refact4j.util;

import junit.framework.AssertionFailedError;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public abstract class SerializableTestCase {

    private Serializable serializable;

    protected SerializableTestCase() {
        super();
    }

    protected abstract Serializable createSerializableInstance()
            ;

    @Before
    public void setUp() throws Exception {
        this.serializable = createSerializableInstance();
        try {
            assertNotNull("createSerializableInstance() returned null",
                    this.serializable);
        } catch (AssertionFailedError ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    /**
     * Verifies that an instance of the class under test can be serialized and
     * deserialized without error.
     *
     * @throws Exception
     */
    @Test
    public final void testSerializability() throws Exception {
        byte[] serial = SerializerHelper.serialize(this.serializable);
        Serializable deserial = (Serializable) SerializerHelper
                .deserialize(serial);

        checkObject(this.serializable, deserial);
    }

    protected void checkObject(Serializable expected, Serializable actual) {
        assertEquals(expected, actual);
    }
}
