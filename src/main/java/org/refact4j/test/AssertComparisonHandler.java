package org.refact4j.test;

import org.refact4j.functor.BinaryPredicate;

public interface AssertComparisonHandler<T> extends BinaryPredicate<T, T>, AssertionHandler {
}
