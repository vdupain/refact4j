package org.refact4j.functor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.refact4j.eom.EntityStringifierRepo;
import org.refact4j.eom.model.impl.EntityDescriptorImpl;
import org.refact4j.model.BarDesc;
import org.refact4j.model.DummyStringifierRepository;
import org.refact4j.model.FooDesc;
import org.refact4j.util.StringHelper;

public class StringifierTest {

    @Test
    public void testStringifierRepository() {
        EntityStringifierRepo stringifierRepository = DummyStringifierRepository.get();
        assertEquals(DummyStringifierRepository.FOO_STRINGIFIER, stringifierRepository
                .lookup(FooDesc.INSTANCE));
        assertEquals(DummyStringifierRepository.BAR_STRINGIFIER, stringifierRepository
                .lookup(BarDesc.INSTANCE));
        try {
            stringifierRepository.lookup(new EntityDescriptorImpl("?"));
            fail("Exception Expected");
        } catch (Exception e) {
            assertEquals("Missing Stringifier for EntityDescriptor '?' in repository", e.getMessage());
        }

    }

    @Test
    public void testDefaultStringifier() {
        assertEquals(Integer.toString(1), AbstractStringifier.DEFAULT.stringify(1));
        assertEquals("abcdef", AbstractStringifier.DEFAULT.stringify("abcdef"));
        assertEquals("abcdef", AbstractStringifier.DEFAULT.eval("abcdef"));
        assertEquals("null", AbstractStringifier.DEFAULT.stringify(null));
        assertEquals(StringHelper.FILE_SEPARATOR, AbstractStringifier.DEFAULT
                .stringify(StringHelper.FILE_SEPARATOR));
        assertEquals(FooDesc.INSTANCE.getName(), FooDesc.INSTANCE.toString());
    }
}
