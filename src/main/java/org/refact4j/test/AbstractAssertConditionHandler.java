package org.refact4j.test;

public abstract class AbstractAssertConditionHandler implements AssertConditionHandler {

    private String message;
    private Boolean condition;

    AbstractAssertConditionHandler() {

    }

    public AbstractAssertConditionHandler(String message, Boolean condition) {
        this.message = message;
        this.condition = condition;
    }

    protected abstract void assertion(Boolean condition);

    public Boolean apply(Boolean condition) {
        assertion(condition);
        return true;
    }

    public void assertion() {
        this.apply(condition);
    }

    public Boolean getCondition() {
        return condition;
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
