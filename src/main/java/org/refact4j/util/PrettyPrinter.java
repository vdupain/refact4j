package org.refact4j.util;

import org.refact4j.eom.EntityFieldComparator;
import org.refact4j.eom.EntityFieldComparator.EntityFieldComparatorVisitor;
import org.refact4j.eom.GetEntityFieldFunction;
import org.refact4j.eom.GetEntityFieldFunction.GetEntityFieldFunctorVisitor;
import org.refact4j.eom.model.Field;
import org.refact4j.eom.model.impl.FieldStringifier;
import org.refact4j.expr.Expression;
import org.refact4j.expr.Expression.ExpressionVisitor;
import org.refact4j.functor.BinaryCompose;
import org.refact4j.functor.BinaryCompose.BinaryComposeVisitor;
import org.refact4j.functor.CompositeUnaryPredicate;
import org.refact4j.functor.CompositeUnaryPredicate.CompositeUnaryPredicateVisitor;
import org.refact4j.functor.GetFieldFunction;
import org.refact4j.functor.GetFieldFunction.GetFieldFunctionVisitor;
import org.refact4j.functor.UnaryCompose;
import org.refact4j.functor.UnaryCompose.UnaryComposeVisitor;
import org.refact4j.functor.commons.*;
import org.refact4j.functor.commons.Between.BetweenVisitor;
import org.refact4j.functor.commons.In.InVisitor;
import org.refact4j.functor.commons.InstanceOf.InstanceOfVisitor;
import org.refact4j.functor.commons.Like.LikeVisitor;
import org.refact4j.functor.commons.NotIn.NotInVisitor;
import org.refact4j.functor.commons.StringLength.StringLengthVisitor;
import org.refact4j.functor.comparison.*;
import org.refact4j.functor.comparison.NotNull.NotNullVisitor;
import org.refact4j.functor.comparison.Null.NullVisitor;
import org.refact4j.functor.identity.Identity;
import org.refact4j.functor.identity.Identity.IdentityVisitor;
import org.refact4j.functor.logical.And;
import org.refact4j.functor.logical.LogicalVisitor;
import org.refact4j.functor.logical.Not;
import org.refact4j.functor.logical.Or;
import org.refact4j.visitor.Visitable;

public class PrettyPrinter implements ExpressionVisitor, BinaryComposeVisitor, UnaryComposeVisitor,
        CompositeUnaryPredicateVisitor, EntityFieldComparatorVisitor, ComparisonVisitor, LogicalVisitor,
        BetweenVisitor, NullVisitor, NotNullVisitor, InVisitor, NotInVisitor, LikeVisitor, InstanceOfVisitor,
        GetEntityFieldFunctorVisitor, GetFieldFunctionVisitor, StringLengthVisitor, IdentityVisitor {

    private final FieldStringifier stringifier = new FieldStringifier() {

        @Override
        public String stringify(Field field) {
            return field.getFullName();
        }

    };
    private StringBuffer buf;
    private Expression expression;

    public PrettyPrinter() {
        this.buf = null;
    }

    public String toString(Object object) {
        if (object instanceof Visitable) {
            return toString((Visitable) object);
        }
        return toString(object.toString());
    }

    public String toString(Visitable visitable) {
        buf = new StringBuffer(256);
        visitable.accept(this);
        return buf.toString();
    }

    public void visitEntityFieldComparator(EntityFieldComparator fieldComparator) {
        buf.append('(');
        buf.append(this.stringifier.stringify(fieldComparator.getField()));
        ((Visitable) fieldComparator.getBiFunction()).accept(this);
        buf.append(fieldComparator.getValue());
        buf.append(')');
    }

    public void visitCompositeUnaryPredicate(CompositeUnaryPredicate<?> compositeUnaryPredicate) {
        buf.append('(');
        java.util.function.Function<?, ?> function = compositeUnaryPredicate.getFunction();
        ((Visitable) function).accept(this);
        ((Visitable) compositeUnaryPredicate.getBiFunction()).accept(this);
        buf.append(compositeUnaryPredicate.getConstantUnaryFunctor().getConstant());
        buf.append(')');
    }

    public void visitGetEntityFieldFunctor(GetEntityFieldFunction getEntityFieldFunctor) {
        buf.append(this.stringifier.stringify(getEntityFieldFunctor.getField()));
    }

    public void visitGetFieldFunction(GetFieldFunction getFieldFunction) {
        buf.append(getFieldFunction.getFieldName());
    }

    public void visitBinaryCompose(BinaryCompose binaryCompose) {
        buf.append('(');
        ((Visitable) binaryCompose.getFirstFunction()).accept(this);
        ((Visitable) binaryCompose.getBiFunction()).accept(this);
        ((Visitable) binaryCompose.getSecondFunction()).accept(this);
        buf.append(')');
    }

    public void visitUnaryCompose(UnaryCompose unaryCompose) {
        buf.append('(');
        java.util.function.Function secondFunction = unaryCompose.getBefore();
        java.util.function.Function firstFunction = unaryCompose.getFunction();
        if (firstFunction instanceof Between || firstFunction instanceof In
                || firstFunction instanceof NotIn) {
            ((Visitable) secondFunction).accept(this);
            ((Visitable) firstFunction).accept(this);
        } else {
            ((Visitable) firstFunction).accept(this);
            ((Visitable) secondFunction).accept(this);
        }
        buf.append(')');
    }

    public void visitEqual(Equal equal) {
        buf.append('=');
    }

    public void visitGreater(Greater greater) {
        buf.append('>');
    }

    public void visitGreaterEqual(GreaterEqual greaterEqual) {
        buf.append(">=");
    }

    public void visitNotEqual(NotEqual notEqual) {
        buf.append("!=");
    }

    public void visitLess(Less less) {
        buf.append("<");
    }

    public void visitLessEqual(LessEqual lessEqual) {
        buf.append("<=");
    }

    public void visitMin(Min min) {
        buf.append("MIN");
    }

    public void visitMax(Max max) {
        buf.append("MAX");
    }

    public void visitAnd(And and) {
        buf.append(" AND ");
    }

    public void visitOr(Or or) {
        buf.append(" OR ");
    }

    public void visitNot(Not not) {
        buf.append(" NOT ");
    }

    public void visitLike(Like like) {
        buf.append(" LIKE ");
    }

    public void visitInstanceOf(InstanceOf instanceOf) {
        buf.append(" INSTANCEOF ");
    }

    public void visitExpression(Expression expression) {
        this.expression = expression;
        ((Visitable) expression.getPredicate()).accept(this);
    }

    public void visitBetween(Between<?> between) {
        buf.append(" BETWEEN ");
        buf.append(between.getInfValue());
        buf.append(" AND ");
        buf.append(between.getSupValue());
    }

    public void visitNull(Null<?> nul) {
        buf.append(" IS NULL ");
    }

    public void visitNotNull(NotNull<?> notNull) {
        buf.append(" IS NOT NULL ");
    }

    public void visitIn(In<?> in) {
        buf.append(" IN ");
        appendValues(in.getValues());
    }

    public void visitNotIn(NotIn<?> notIn) {
        buf.append(" NOT IN ");
        appendValues(notIn.getValues());
    }

    public void visitStringLength(StringLength stringLength) {
        buf.append(this.expression.getPropertyName()).append(".length");
    }

    public void visitIdentity(Identity identityFunctor) {
        buf.append(this.expression.getPropertyName());
    }

    private void appendValues(Object[] values) {
        buf.append("(");
        for (int i = 0; i < values.length; i++) {
            buf.append(values[i]);
            if (i < values.length - 1) {
                buf.append(", ");
            }
        }
        buf.append(")");
    }

    public void visit(Visitable visitable) {
    }

}
