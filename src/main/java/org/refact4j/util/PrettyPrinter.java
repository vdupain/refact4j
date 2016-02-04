package org.refact4j.util;

import org.refact4j.eom.EntityFieldComparator;
import org.refact4j.eom.EntityFieldComparator.EntityFieldComparatorVisitor;
import org.refact4j.eom.GetEntityFieldFunction;
import org.refact4j.eom.GetEntityFieldFunction.GetEntityFieldFunctorVisitor;
import org.refact4j.eom.model.impl.Stringifiers;
import org.refact4j.expr.Expression;
import org.refact4j.expr.Expression.ExpressionVisitor;
import org.refact4j.function.BinaryCompose;
import org.refact4j.function.BinaryCompose.BinaryComposeVisitor;
import org.refact4j.function.ComposeFunction;
import org.refact4j.function.ComposeFunction.UnaryComposeVisitor;
import org.refact4j.function.CompositeUnaryPredicate;
import org.refact4j.function.CompositeUnaryPredicate.CompositeUnaryPredicateVisitor;
import org.refact4j.function.GetFieldFunction;
import org.refact4j.function.GetFieldFunction.GetFieldFunctionVisitor;
import org.refact4j.function.commons.*;
import org.refact4j.function.commons.Between.BetweenVisitor;
import org.refact4j.function.commons.In.InVisitor;
import org.refact4j.function.commons.InstanceOf.InstanceOfVisitor;
import org.refact4j.function.commons.Like.LikeVisitor;
import org.refact4j.function.commons.StringLength.StringLengthVisitor;
import org.refact4j.function.comparison.*;
import org.refact4j.function.comparison.Null.NullVisitor;
import org.refact4j.function.logical.And;
import org.refact4j.function.logical.LogicalVisitor;
import org.refact4j.function.logical.Not;
import org.refact4j.function.logical.Or;
import org.refact4j.visitor.Visitable;

public class PrettyPrinter implements ExpressionVisitor, BinaryComposeVisitor, UnaryComposeVisitor,
        CompositeUnaryPredicateVisitor, EntityFieldComparatorVisitor, ComparisonVisitor, LogicalVisitor,
        BetweenVisitor, NullVisitor, InVisitor, LikeVisitor, InstanceOfVisitor,
        GetEntityFieldFunctorVisitor, GetFieldFunctionVisitor, StringLengthVisitor {

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
        buf.append(Stringifiers.FIELD_FULLNAME.stringify(fieldComparator.getField()));
        visit(fieldComparator.getBiFunction());
        buf.append(fieldComparator.getValue());
        buf.append(')');
    }

    public void visitCompositeUnaryPredicate(CompositeUnaryPredicate<?> compositeUnaryPredicate) {
        buf.append('(');
        java.util.function.Function<?, ?> function = compositeUnaryPredicate.getFunction();
        visit(function);
        visit(compositeUnaryPredicate.getBiFunction());
        buf.append(compositeUnaryPredicate.getValue());
        buf.append(')');
    }

    public void visitGetEntityFieldFunctor(GetEntityFieldFunction getEntityFieldFunctor) {
        buf.append(Stringifiers.FIELD_FULLNAME.stringify(getEntityFieldFunctor.getField()));
    }

    public void visitGetFieldFunction(GetFieldFunction getFieldFunction) {
        buf.append(getFieldFunction.getFieldName());
    }

    public void visitBinaryCompose(BinaryCompose binaryCompose) {
        buf.append('(');
        visit(binaryCompose.getFirstFunction());
        visit(binaryCompose.getBiFunction());
        visit(binaryCompose.getSecondFunction());
        buf.append(')');
    }

    public void visitUnaryCompose(ComposeFunction composeFunction) {
        buf.append('(');
        java.util.function.Function secondFunction = composeFunction.getBefore();
        java.util.function.Function firstFunction = composeFunction.getFunction();
        if (firstFunction instanceof Between || firstFunction instanceof In) {
            visit(secondFunction);
            visit(firstFunction);
        } else {
            visit(firstFunction);
            visit(secondFunction);
        }
        buf.append(')');
    }

    private void visit(Object function) {
        if (function instanceof Visitable)
            ((Visitable) function).accept(this);
        else buf.append(this.expression.getPropertyName());
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
        visit(expression.getPredicate());
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

    public void visitIn(In<?> in) {
        buf.append(" IN ");
        appendValues(in.getValues());
    }

    public void visitStringLength(StringLength stringLength) {
        buf.append(this.expression.getPropertyName()).append(".length");
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
