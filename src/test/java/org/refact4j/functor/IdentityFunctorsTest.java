package org.refact4j.functor;

import static org.junit.Assert.assertSame;
import org.junit.Test;
import org.refact4j.functor.identity.Identity;
import org.refact4j.functor.identity.Project1st;
import org.refact4j.functor.identity.Project2nd;


public class IdentityFunctorsTest {

    @Test
    public void testIdentity() {
        java.util.function.Function<Object,?> identity = new Identity<Object>();
        assertSame(identity, identity.apply(identity));
        assertSame(this, identity.apply(this));
    }

    @Test
    public void testProject1st() {
        BinaryFunctor<Object, Object, Object> project1st = new Project1st<Object, Object>();
        assertSame(project1st, project1st.eval(project1st, "abc"));
        assertSame(this, project1st.eval(this, "abc"));
    }

    @Test
    public void testProject2nd() {
        BinaryFunctor<Object, Object, Object> project2nd = new Project2nd<Object, Object>();
        assertSame(project2nd, project2nd.eval("abc", project2nd));
        assertSame(this, project2nd.eval("abc", this));
    }

}
