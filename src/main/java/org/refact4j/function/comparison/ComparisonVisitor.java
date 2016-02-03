package org.refact4j.function.comparison;

import org.refact4j.function.comparison.Equal.EqualVisitor;
import org.refact4j.function.comparison.Greater.GreaterVisitor;
import org.refact4j.function.comparison.GreaterEqual.GreaterEqualVisitor;
import org.refact4j.function.comparison.Less.LessVisitor;
import org.refact4j.function.comparison.LessEqual.LessEqualVisitor;
import org.refact4j.function.comparison.Max.MaxVisitor;
import org.refact4j.function.comparison.Min.MinVisitor;
import org.refact4j.function.comparison.NotEqual.NotEqualVisitor;

public interface ComparisonVisitor<T> extends EqualVisitor<T>, NotEqualVisitor<T>, LessVisitor<T>, GreaterVisitor<T>,
        LessEqualVisitor<T>, GreaterEqualVisitor<T>, MinVisitor<T>, MaxVisitor<T> {
}
