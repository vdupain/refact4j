package org.refact4j.functor;

import org.refact4j.visitor.Visitable;
import org.refact4j.visitor.Visitor;

import java.lang.reflect.Method;

public class GetFieldFunctor implements UnaryFunctor, Visitable {

    private final String fieldName;

    public GetFieldFunctor(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object eval(Object arg) {
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
        if (visitor instanceof GetFieldFunctorVisitor) {
            ((GetFieldFunctorVisitor) visitor).visitGetFieldFunctor(this);
        }
    }

    public interface GetFieldFunctorVisitor extends Visitor {
        void visitGetFieldFunctor(GetFieldFunctor functor);
    }

}
