package org.refact4j.test;

public abstract class AbstractAssertComparisonHandler<T> implements AssertComparisonHandler<T> {

    String message;
    private T expected;
    private T actual;

    AbstractAssertComparisonHandler() {

    }

    public AbstractAssertComparisonHandler(String message, T expected, T actual) {
        this.message = message;
        this.expected = expected;
        this.actual = actual;
    }

    protected abstract void assertion(T expected, T actual);

    public boolean test(T expected, T actual) {
        assertion(expected, actual);
        return true;
    }

    public void assertion() {
        this.apply(expected, actual);
    }

    public Object getExpected() {
        return expected;
    }

    public void setExpected(T expected) {
        this.expected = expected;
    }

    public Object getActual() {
        return actual;
    }

    public void setActual(T actual) {
        this.actual = actual;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
