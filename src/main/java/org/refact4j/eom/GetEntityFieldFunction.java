package org.refact4j.eom;

import org.refact4j.eom.model.Field;
import org.refact4j.visitor.Visitable;
import org.refact4j.visitor.Visitor;

import java.util.function.Function;

public class GetEntityFieldFunction implements Function<EntityObject, Object>, Visitable {

    private final String fieldName;

    private Field field;

    public GetEntityFieldFunction(Field field) {
        this(field.getName());
        this.field = field;
    }

    private GetEntityFieldFunction(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object apply(EntityObject entityObject) {
        return entityObject.get(fieldName);
    }

    public String toString() {
        return fieldName;
    }

    public Field getField() {
        return field;
    }

    public void accept(Visitor visitor) {
        if (visitor instanceof GetEntityFieldFunctorVisitor) {
            ((GetEntityFieldFunctorVisitor) visitor).visitGetEntityFieldFunctor(this);
        }
    }

    public interface GetEntityFieldFunctorVisitor extends Visitor {
        void visitGetEntityFieldFunctor(GetEntityFieldFunction functor);
    }

}
