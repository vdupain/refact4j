package org.refact4j.functor;

import static org.junit.Assert.assertSame;
import org.junit.Test;
import org.refact4j.functor.identity.Identity;


public class IdentityFunctorsTest {

    @Test
    public void testIdentity() {
        java.util.function.Function<Object,?> identity = new Identity<Object>();
        assertSame(identity, identity.apply(identity));
        assertSame(this, identity.apply(this));
    }

}
