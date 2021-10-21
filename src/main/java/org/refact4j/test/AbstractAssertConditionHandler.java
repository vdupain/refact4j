package org.refact4j.test;

public abstract class AbstractAssertConditionHandler implements AssertConditionHandler {

    private String message;
    private Boolean condition;

    AbstractAssertConditionHandler() {

    }

    protected abstract void assertion(Boolean condition);

    public boolean test(Boolean condition) {
        assertion(condition);
        return true;
    }

    public void assertion() {
        this.test(condition);
    }

    public void setCondition(Boolean condition) {
        this.condition = condition;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
