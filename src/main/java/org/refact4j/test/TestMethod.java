package org.refact4j.test;

import java.util.ArrayList;
import java.util.List;

public class TestMethod {

    private String name;
    private TestMethodHandler testMethodHandler;
    private final List<AssertionHandler> assertions = new ArrayList<AssertionHandler>();

    public TestMethodHandler getTestMethodHandler() {
        return testMethodHandler;
    }

    public void setTestMethodHandler(TestMethodHandler testMethodHandler) {
        this.testMethodHandler = testMethodHandler;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addAssertion(AssertionHandler assertionHandler) {
        assertions.add(assertionHandler);
    }

    public void checkAssertions() {
        for (AssertionHandler assertion : assertions) {
            assertion.assertion();
        }
    }

}
