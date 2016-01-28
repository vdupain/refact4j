package org.refact4j.functor;

import org.junit.Test;
import org.refact4j.functor.identity.Identity;

import static org.junit.Assert.assertSame;


public class IdentityFunctorsTest {

    @Test
    public void testIdentity() {
        java.util.function.Function<Object, ?> identity = new Identity<>();
        assertSame(identity, identity.apply(identity));
        assertSame(this, identity.apply(this));
    }

}
