package org.refact4j.util;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

public class BeanHelperTest {

    @Test
    public void testGetMutator() throws Exception {
        Method valueMutator = BeanHelper.getMutator(Dummy.class, "value", new Class[]{String.class});
        Method enableMutator = BeanHelper.getMutator(Dummy.class, "enabled", new Class[]{boolean.class});
        Method dummyMutator = BeanHelper.getMutator(Dummy.class, "dummy", new Class[]{double.class});

        Assert.assertEquals("setValue", valueMutator.getName());
        Assert.assertEquals(Void.TYPE, valueMutator.getReturnType());
        Assert.assertEquals(1, valueMutator.getParameterTypes().length);
        Assert.assertEquals(String.class, valueMutator.getParameterTypes()[0]);
        Assert.assertTrue(BeanHelper.isMutator(valueMutator));
        Assert.assertFalse(BeanHelper.isAccessor(valueMutator));

        Assert.assertEquals("setEnabled", enableMutator.getName());
        Assert.assertEquals(Void.TYPE, enableMutator.getReturnType());
        Assert.assertEquals(1, enableMutator.getParameterTypes().length);
        Assert.assertEquals(boolean.class, enableMutator.getParameterTypes()[0]);
        Assert.assertTrue(BeanHelper.isMutator(enableMutator));
        Assert.assertFalse(BeanHelper.isAccessor(enableMutator));

        Assert.assertNull(dummyMutator);
        dummyMutator = Dummy.class.getDeclaredMethod("dummy", new Class[]{double.class});
        Assert.assertFalse(BeanHelper.isMutator(dummyMutator));
        Assert.assertFalse(BeanHelper.isAccessor(dummyMutator));
    }

    @Test
    public void testGetAccessor() throws Exception {
        Method valueAccessor = BeanHelper.getAccessor(Dummy.class, "value");
        Method enableAccessor = BeanHelper.getAccessor(Dummy.class, "enabled");
        Method dummyAccessor = BeanHelper.getAccessor(Dummy.class, "dummy");

        Assert.assertEquals("getValue", valueAccessor.getName());
        Assert.assertEquals(String.class, valueAccessor.getReturnType());
        Assert.assertEquals(0, valueAccessor.getParameterTypes().length);
        Assert.assertTrue(BeanHelper.isAccessor(valueAccessor));
        Assert.assertFalse(BeanHelper.isMutator(valueAccessor));

        Assert.assertEquals("isEnabled", enableAccessor.getName());
        Assert.assertEquals(boolean.class, enableAccessor.getReturnType());
        Assert.assertEquals(0, enableAccessor.getParameterTypes().length);
        Assert.assertTrue(BeanHelper.isAccessor(enableAccessor));
        Assert.assertFalse(BeanHelper.isMutator(enableAccessor));

        Assert.assertNull(dummyAccessor);
        dummyAccessor = Dummy.class.getMethod("dummy");
        Assert.assertFalse(BeanHelper.isAccessor(dummyAccessor));
        Assert.assertFalse(BeanHelper.isMutator(dummyAccessor));
    }

    @Test
    public void testGetPropertyName() {
        try {
            BeanHelper.getPropertyName("dummy");
            Assert.fail();
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Method 'dummy' not a JavaBean method.", e.getMessage());
        }
        Assert.assertEquals("enabled", BeanHelper.getPropertyName("isEnabled"));
        Assert.assertEquals("enabled", BeanHelper.getPropertyName("setEnabled"));
        Assert.assertEquals("value", BeanHelper.getPropertyName("getValue"));
        Assert.assertEquals("value", BeanHelper.getPropertyName("setValue"));
    }

    interface Dummy {

        String getValue();

        void setValue(String value);

        boolean isEnabled();

        void setEnabled(boolean enabled);

        double dummy();

        void dummy(double d);

    }
}
