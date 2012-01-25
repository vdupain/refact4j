package org.refact4j.functor.comparison;

import org.refact4j.functor.comparison.Equal.EqualVisitor;
import org.refact4j.functor.comparison.Greater.GreaterVisitor;
import org.refact4j.functor.comparison.GreaterEqual.GreaterEqualVisitor;
import org.refact4j.functor.comparison.Less.LessVisitor;
import org.refact4j.functor.comparison.LessEqual.LessEqualVisitor;
import org.refact4j.functor.comparison.Max.MaxVisitor;
import org.refact4j.functor.comparison.Min.MinVisitor;
import org.refact4j.functor.comparison.NotEqual.NotEqualVisitor;

public interface ComparisonVisitor<T> extends EqualVisitor<T>, NotEqualVisitor<T>, LessVisitor<T>, GreaterVisitor<T>,
        LessEqualVisitor<T>, GreaterEqualVisitor<T>, MinVisitor<T>, MaxVisitor<T> {
}
