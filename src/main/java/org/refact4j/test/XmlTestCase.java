package org.refact4j.test;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class XmlTestCase extends TestCase {
    private AfterTestHandler afterTestFunctor;
    private BeforeTestHandler beforeTestFunctor;
    private final List<TestMethod> tests = new ArrayList<TestMethod>();

    @Override
    protected void setUp() throws Exception {
        if (beforeTestFunctor != null) {
            beforeTestFunctor.apply(this);
        }
    }

    @Override
    protected void tearDown() throws Exception {
        if (afterTestFunctor != null) {
            afterTestFunctor.apply(this);
        }
    }

    public void setAfterTestFunctor(AfterTestHandler afterTestFunctor) {
        this.afterTestFunctor = afterTestFunctor;
    }

    public void setBeforeTestFunctor(BeforeTestHandler beforeTestFunctor) {
        this.beforeTestFunctor = beforeTestFunctor;
    }

    private void _runTest() throws Throwable {
        for (TestMethod testMethod : tests) {
            this.setUp();
            testMethod.getTestMethodHandler().apply(this);
            testMethod.checkAssertions();
            this.tearDown();
        }
    }

    public void addTestMethod(TestMethod testMethod) {
        tests.add(testMethod);
    }

    @Override
    protected void runTest() throws Throwable {
        _runTest();
    }

    public void testNoop() {
        //noop
    }
}
