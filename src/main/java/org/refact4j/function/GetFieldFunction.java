package org.refact4j.function;

import org.refact4j.visitor.Visitable;
import org.refact4j.visitor.Visitor;

import java.lang.reflect.Method;

public class GetFieldFunction implements Visitable, java.util.function.Function {

    private final String fieldName;

    public GetFieldFunction(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object apply(Object arg) {
        Object object;
        try {
            Method method = arg.getClass().getMethod("get" + fieldName);
            object = method.invoke(arg);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return object;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String toString() {
        return fieldName;
    }

    public void accept(Visitor visitor) {
        if (visitor instanceof GetFieldFunctionVisitor) {
            ((GetFieldFunctionVisitor) visitor).visitGetFieldFunction(this);
        }
    }

    public interface GetFieldFunctionVisitor extends Visitor {
        void visitGetFieldFunction(GetFieldFunction functor);
    }

}
