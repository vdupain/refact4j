package org.refact4j.function;

import org.junit.Test;
import org.refact4j.eom.EntityStringifierRepo;
import org.refact4j.eom.model.impl.EntityDescriptorImpl;
import org.refact4j.model.BarDesc;
import org.refact4j.model.DummyStringifierRepository;
import org.refact4j.model.FooDesc;
import org.refact4j.util.StringHelper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class StringifierTest {

    @Test
    public void testStringifierRepository() {
        EntityStringifierRepo stringifierRepository = DummyStringifierRepository.get();
        assertEquals(DummyStringifierRepository.FOO_STRINGIFIER, stringifierRepository
                .get(FooDesc.INSTANCE));
        assertEquals(DummyStringifierRepository.BAR_STRINGIFIER, stringifierRepository
                .get(BarDesc.INSTANCE));
        assertNull(stringifierRepository.get(new EntityDescriptorImpl("?")));
    }

    @Test
    public void testDefaultStringifier() {
        assertEquals(Integer.toString(1), Stringifier.DEFAULT.stringify(1));
        assertEquals("abcdef", Stringifier.DEFAULT.stringify("abcdef"));
        assertEquals("abcdef", Stringifier.DEFAULT.stringify("abcdef"));
        assertEquals("null", Stringifier.DEFAULT.stringify(null));
        assertEquals(StringHelper.FILE_SEPARATOR, Stringifier.DEFAULT
                .stringify(StringHelper.FILE_SEPARATOR));
        assertEquals(FooDesc.INSTANCE.getName(), FooDesc.INSTANCE.toString());
    }
}
