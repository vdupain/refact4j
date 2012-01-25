package org.refact4j.eom;

import org.refact4j.eom.model.Field;
import org.refact4j.visitor.Visitable;
import org.refact4j.visitor.Visitor;

public class GetEntityFieldFunctor implements EntityFunctor<Object>, Visitable {

    private final String fieldName;

    private Field field;

    public GetEntityFieldFunctor(Field field) {
        this(field.getName());
        this.field = field;
    }

    public GetEntityFieldFunctor(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object eval(EntityObject entityObject) {
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
        void visitGetEntityFieldFunctor(GetEntityFieldFunctor functor);
    }

}
